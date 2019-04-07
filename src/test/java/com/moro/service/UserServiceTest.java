package com.moro.service;

import com.moro.dao.repository.UserRepository;
import com.moro.model.exception.EntityNotFoundException;
import com.moro.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserRoleService userRoleService;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void findNonexistentUserById() {
        Mockito.when(userRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> userService.findUserById(1)
        );
    }

    @Test
    void deleteNonexistentUserById() {
        Mockito.when(userRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> userService.deleteUserById(1)
        );
    }
}
