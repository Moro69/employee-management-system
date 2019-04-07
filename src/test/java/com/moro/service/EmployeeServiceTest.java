package com.moro.service;

import com.moro.dao.repository.EmployeeRepository;
import com.moro.model.exception.EntityNotFoundException;
import com.moro.service.impl.EmployeeServiceImpl;
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
class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Test
    void findNonexistentEmployeeById() {
        Mockito.when(employeeRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> employeeService.findEmployeeById(1)
        );
    }

    @Test
    void deleteNonexistentEmployeeById() {
        Mockito.when(employeeRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> employeeService.deleteEmployeeById(1)
        );
    }
}
