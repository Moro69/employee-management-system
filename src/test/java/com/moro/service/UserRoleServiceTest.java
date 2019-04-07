package com.moro.service;

import com.moro.dao.repository.UserRoleRepository;
import com.moro.model.enums.UserRoleEnum;
import com.moro.model.exception.EntityNotFoundException;
import com.moro.service.impl.UserRoleServiceImpl;
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
class UserRoleServiceTest {

    @Mock
    private UserRoleRepository userRoleRepository;

    @InjectMocks
    private UserRoleServiceImpl userRoleService;

    @Test
    void findNonexistentRoleById() {
        Mockito.when(userRoleRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> userRoleService.findUserRoleById(1)
        );
    }

    @Test
    void findNonexistentRoleByName() {
        Mockito.when(userRoleRepository.findByName(ArgumentMatchers.any()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> userRoleService.findUserRoleByName(UserRoleEnum.USER)
        );
    }
}
