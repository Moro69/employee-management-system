package com.moro.service.validator;

import com.moro.dao.repository.UserRepository;
import com.moro.model.entity.User;
import com.moro.model.exception.ApiException;
import com.moro.model.exception.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Slf4j
@Service
public class UserValidator {

    private UserRepository userRepository;

    @Autowired
    public UserValidator(final UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User validateUpdateAuthorities(final Principal principal,
                                          final Integer userId) {
        User user = userRepository
                .findByEmail(principal.getName())
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No user with email %s", principal.getName())));

        if (!user.getUserId().equals(userId)) {
                throw new ApiException(HttpStatus.FORBIDDEN, "Forbidden");
        }

        return user;
    }
}
