package com.moro.web.rest;

import com.moro.model.dto.RegistrationModel;
import com.moro.model.dto.UserDto;
import com.moro.model.entity.User;
import com.moro.model.entity.VerificationToken;
import com.moro.service.UserService;
import com.moro.service.VerificationTokenService;
import com.moro.service.impl.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.security.Principal;

@RestController
@RequestMapping(value = "/user")
@Slf4j
public class UserRestController {

    private static final String ADMIN_EMAIL = "dmitriy.shestak1337@gmail.com";

    private UserService userService;
    private EmailSenderService emailSenderService;
    private VerificationTokenService tokenService;

    @Autowired
    public UserRestController(final UserService userService,
                              final EmailSenderService emailSenderService,
                              final VerificationTokenService tokenService) {
        this.userService = userService;
        this.emailSenderService = emailSenderService;
        this.tokenService = tokenService;
    }

    @GetMapping
    public ResponseEntity<Page<User>> findAllUsers(@RequestParam
                                                       @PositiveOrZero final int page,
                                                   @RequestParam
                                                       @PositiveOrZero final int pageSize) {
        log.info("Finding all users");

        return new ResponseEntity<>(
                userService.findAllUsers(PageRequest.of(page, pageSize)), HttpStatus.FOUND);
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
        mailMessage.setText("To verify account, please click here: "

                + "http://localhost:8080/user/verify?token=" + verificationToken.getToken());

        emailSenderService.sendEmail(mailMessage);

        return ResponseEntity.ok(user);
    }

    @PostMapping(value = "/verify")
    public ResponseEntity<User> verifyUser(@RequestParam final String token) {
        log.info("Verifying user by token {}", token);

        VerificationToken verificationToken =
                tokenService.findByToken(token);

        return ResponseEntity.ok(userService.verify(verificationToken.getUser().getUserId()));
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody @Valid final UserDto dto,
                                           final Principal principal) {
        log.info("Updating user: {}", dto);

        return ResponseEntity.ok(userService.updateUser(principal, dto));
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity deleteUserById(@PathVariable final int userId) {
        log.info("Deleting user by id {}", userId);

        userService.deleteUserById(userId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @PostMapping(value = "/{userId}/photo")
    public ResponseEntity uploadUserPhoto(final Principal principal,
                                          @PathVariable final int userId,
                                          @RequestParam final MultipartFile photo) {
        log.info("Uploading photo for user with id {} by {}", userId, principal);

        userService.uploadUserPhoto(principal, userId, photo);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}/photo")
    public ResponseEntity<byte[]> downloadUserPhoto(@PathVariable final int userId,
                                                    final Principal principal) {
        log.info("Downloading photo of user with id {} by {}", userId, principal);

        return ResponseEntity
                .ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline")
                .body(userService.getPhotoAsByteArray(userId));
    }

    @DeleteMapping(value = "/{userId}/photo")
    public ResponseEntity deleteUserPhoto(final Principal principal,
                                          @PathVariable final int userId) {
        log.info("Deleting photo of user with id {} by {}", userId, principal);

        userService.deleteUserPhoto(principal, userId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
