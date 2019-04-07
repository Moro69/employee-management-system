package com.moro.service;

import com.moro.dao.repository.DepartmentRepository;
import com.moro.model.exception.EntityNotFoundException;
import com.moro.model.search.DateRange;
import com.moro.model.search.DepartmentFacetedSearch;
import com.moro.service.impl.DepartmentServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    @Test
    void findNonexistentDepartmentById() {
        Mockito.when(departmentRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> departmentService.findDepartmentById(1)
        );
    }

    @Test
    void deleteNonexistentDepartmentById() {
        Mockito.when(departmentRepository.findById(ArgumentMatchers.anyInt()))
                .thenReturn(Optional.empty());

        Assertions.assertThrows(EntityNotFoundException.class,
                () -> departmentService.deleteDepartmentById(1)
        );
    }

    @Test
    void findDepartmentsBetweenInvalidDateRange() {
        Assertions.assertThrows(RuntimeException.class,
                () -> departmentService
                        .findAllDepartments(DepartmentFacetedSearch.builder().
                                dateRange(new DateRange(LocalDate.MAX, LocalDate.MIN))
                                .build())
        );
    }
}
