package com.moro.web.rest;

import com.moro.model.dto.RegistrationModel;
import com.moro.model.entity.Image;
import com.moro.model.entity.User;
import com.moro.model.entity.VerificationToken;
import com.moro.service.StorageService;
import com.moro.service.UserService;
import com.moro.service.VerificationTokenService;
import com.moro.service.impl.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.PositiveOrZero;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserRestController {

    private static final String ADMIN_EMAIL = "dmitriy.shestak1337@gmail.com";

    private UserService userService;
    private EmailSenderService emailSenderService;
    private VerificationTokenService tokenService;
    private StorageService storageService;

    @Autowired
    public UserRestController(final UserService userService,
                              final EmailSenderService emailSenderService,
                              final VerificationTokenService tokenService,
                              final StorageService storageService) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
        this.tokenService = tokenService;
        this.storageService = storageService;
    }

    @GetMapping
    public ResponseEntity<Page<User>> findAllUsers(@RequestParam
                                                       @PositiveOrZero final int page,
                                                   @RequestParam
                                                       @PositiveOrZero final int pageSize) {
        log.info("Finding all users");

        return new ResponseEntity<>(
                userService.findAllUsers(PageRequest.of(page, pageSize)), HttpStatus.FOUND
        );
    }

    @GetMapping(value = "/{userId}")
    public ResponseEntity<User> findUserById(@PathVariable final int userId) {
        log.info("Finding user by id {}", userId);

        return new ResponseEntity<>(
                userService.findUserById(userId),
                HttpStatus.FOUND
        );
    }

    @PostMapping
    public ResponseEntity<User> addUser(@RequestBody @Valid final RegistrationModel model) {
        log.info("Adding new user");

        User user = userService.addUser(model);

        VerificationToken verificationToken = new VerificationToken(user);
        tokenService.saveToken(verificationToken);

        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setTo(ADMIN_EMAIL);
        mailMessage.setSubject("Complete Registration!");
        mailMessage.setText("To verify account, please click here : "

                + "http://localhost:8080/user/verify?token=" + verificationToken.getToken());

        emailSenderService.sendEmail(mailMessage);

        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/verify")
    public ResponseEntity<User> verifyUser(@RequestParam final String token) {
        log.info("Verifying user by token {}", token);

        VerificationToken verificationToken =
                tokenService.findByToken(token);

        verificationToken.getUser().setEnabled(true);

        userService.updateUser(verificationToken.getUser());

        return new ResponseEntity<>(verificationToken.getUser(), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody @Valid final User user) {
        log.info("Updating user");

        return ResponseEntity.ok(userService.updateUser(user));
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity deleteUserById(@PathVariable final int userId) {
        log.info("Deleting user by id {}", userId);

        userService.deleteUserById(userId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}/photo")
    public ResponseEntity uploadUserPhoto(@PathVariable final int userId,
                                          @RequestParam final MultipartFile photo) {

        User user = userService.findUserById(userId);

        if (user.getImage() == null) {
            Image image = new Image();
            image.setUrl(storageService.storeUserPhoto(userId, photo));

            user.setImage(image);
        } else {
            storageService.delete(user.getImage().getUrl());
            user.getImage().setUrl(storageService.storeUserPhoto(userId, photo));
        }

        userService.updateUser(user);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/photo")
    public ResponseEntity downloadUserPhoto(@PathVariable final int userId) {
        Resource file = storageService
                .loadAsResource(userService.findUserById(userId)
                        .getImage().getUrl());

        return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                "inline; filename=\"" + file.getFilename() + "\"").body(file);
    }
}
