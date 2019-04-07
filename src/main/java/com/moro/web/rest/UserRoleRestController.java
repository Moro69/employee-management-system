package com.moro.web.rest;

import com.moro.model.entity.UserRole;
import com.moro.model.enums.UserRoleEnum;
import com.moro.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/userrole")
@Slf4j
public class UserRoleRestController {

    private UserRoleService userRoleService;

    @Autowired
    public UserRoleRestController(final UserRoleService userRoleService) {
        this.userRoleService = userRoleService;
    }


    @GetMapping(value = "/{userRoleId}")
    public ResponseEntity<UserRole> findById(@PathVariable final int userRoleId) {
        log.info("Find user role by id {}", userRoleId);

        return new ResponseEntity<>(userRoleService.findUserRoleById(userRoleId),
                HttpStatus.FOUND);
    }

    @GetMapping(value = "/name/{userRoleName}")
    public ResponseEntity<UserRole> findByName(@PathVariable final String userRoleName) {
        log.info("Find user role by id {}", userRoleName);

        return new ResponseEntity<>(
                userRoleService.findUserRoleByName(UserRoleEnum.fromString(userRoleName)),
                HttpStatus.FOUND);
    }

    @GetMapping
    public ResponseEntity<List<UserRole>> findAll() {
        log.info("Find all user roles");

        return new ResponseEntity<>(userRoleService.findAllUserRoles(), HttpStatus.FOUND);
    }
}
