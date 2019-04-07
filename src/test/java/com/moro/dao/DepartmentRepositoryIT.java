package com.moro.dao;

import com.moro.dao.repository.DepartmentRepository;
import com.moro.model.entity.Department;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolationException;
import java.time.LocalDate;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DepartmentRepositoryIT {

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    void findAllDepartments() {
        Pageable pageRequest = PageRequest.of(0, 1, Sort.Direction.ASC, "name");

        Page<Department> departments = departmentRepository.findAll(pageRequest);

        Assertions.assertTrue(departments.hasContent());
        Assertions.assertTrue(departments.hasContent());
    }

    @Test
    void findAllDepartmentsByFormationDate() {
        Pageable pageRequest = PageRequest.of(0, 2, Sort.Direction.ASC, "formationDate");
        Page<Department> departments =
                departmentRepository
                        .findAllByFormationDateBetween(LocalDate.parse("2013-04-22"),
                                LocalDate.parse("2014-05-05"),
                                pageRequest);

        Assertions.assertEquals(2, departments.getTotalElements());
        Assertions.assertEquals(1, departments.getTotalPages());
    }

    @Test
    void addDepartment() {
        Department department = Department.builder()
                .name("Test department")
                .description("Department of testing")
                .formationDate(LocalDate.parse("2019-01-01"))
                .phoneNumber("+88005553535")
                .build();

        departmentRepository.saveAndFlush(department);

        Assertions.assertNotNull(department.getDepartmentId());
    }

    @Test
    void findDepartmentById() {
        Department department = departmentRepository.findById(1).get();

        Assertions.assertNotNull(department);
    }

    @Test
    void editDepartment() {
        Department department = departmentRepository.findById(1).get();

        String newDescription = "New Description";
        String newName = "New name";

        department.setDescription(newDescription);
        department.setName(newName);

        departmentRepository.saveAndFlush(department);

        Assertions.assertEquals(1, department.getDepartmentId().intValue());
        Assertions.assertEquals(newName, department.getName());
        Assertions.assertEquals(newDescription, department.getDescription());
    }

    @Test
    void deleteDepartmentById() {
        departmentRepository.deleteById(1);

        Optional<Department> department = departmentRepository.findById(1);

        Assertions.assertTrue(department.isEmpty());
    }

    @Test
    void tryAddDepartmentWithInvalidData() {
        Department department = Department.builder()
                .name("Test department")
                .description("Department of testing")
                .phoneNumber("+88005553535")
                .build();

        Assertions.assertThrows(ConstraintViolationException.class,
                () -> departmentRepository.saveAndFlush(department));
    }

    @Test
    void tryAddExistentDepartment() {
        Department department = Department.builder()
                .name("Business")
                .description("Department of testing")
                .formationDate(LocalDate.parse("2019-01-01"))
                .phoneNumber("+88005553535")
                .build();

        Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> departmentRepository.saveAndFlush(department));
    }
}
