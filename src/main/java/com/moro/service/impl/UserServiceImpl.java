package com.moro.service.impl;

import com.moro.dao.repository.UserRepository;
import com.moro.model.dto.RegistrationModel;
import com.moro.model.entity.User;
import com.moro.model.enums.UserRoleEnum;
import com.moro.model.exception.EntityNotFoundException;
import com.moro.service.UserRoleService;
import com.moro.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRoleService userRoleService;

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(final UserRoleService userRoleService,
                           final UserRepository userRepository) {
        this.userRoleService = userRoleService;
        this.userRepository = userRepository;
    }

    @Override
    public Page<User> findAllUsers(final Pageable pageable) {
        log.info("Finding all users {}", pageable);

        return userRepository.findAll(pageable);
    }

    @Override
    public User findUserById(final Integer userId) {
        log.info("Finding user by id {}", userId);

        return tryFindUserById(userId);
    }

    @Override
    public User findUserByEmail(final String email) {
        log.info("Finding user by email {}", email);

        return userRepository.findByEmail(email).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("No user with email %s", email))
        );
    }

    @Override
    public User addUser(final RegistrationModel model) {
        log.info("Adding new user");

        User user = new User(model);

        user.setUserRole(userRoleService.findUserRoleByName(UserRoleEnum.USER));
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        user.setEnabled(false);

        return userRepository.saveAndFlush(user);
    }

    @Override
    public User updateUser(final User user) {
        log.info("Updating user");

        return userRepository.saveAndFlush(user);
    }

    @Override
    public void deleteUserById(final Integer userId) {
        log.info("Deleting user by id {}", userId);

        userRepository.delete(tryFindUserById(userId));
    }

    private User tryFindUserById(final Integer userId) {
        return userRepository.findById(userId).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("No user with id %d", userId))
        );
    }
}