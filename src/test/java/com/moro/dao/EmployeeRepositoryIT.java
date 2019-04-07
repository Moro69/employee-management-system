package com.moro.dao;

import com.moro.dao.repository.EmployeeRepository;
import com.moro.model.entity.Address;
import com.moro.model.entity.Department;
import com.moro.model.entity.Education;
import com.moro.model.entity.Employee;
import com.moro.model.enums.EmployeeSortParam;
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
import java.util.HashSet;
import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class EmployeeRepositoryIT {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    void findAllEmployees() {
        Pageable pageRequest = PageRequest.of(0,
                2, Sort.Direction.ASC,
                EmployeeSortParam.BIRTH_DATE.getSortParam());

        Page<Employee> employees =
                employeeRepository
                        .findAll(1,
                                null,
                                "Belarus",
                                2600,
                                null,
                                pageRequest);

        Assertions.assertTrue(employees.hasContent());
    }

    @Test
    void addNewEmployee() {
        Employee employee = getEmployee();

        employeeRepository.saveAndFlush(employee);

        Assertions.assertNotNull(employee.getEmployeeId());
        Assertions.assertNotNull(employee.getAddress().getAddressId());
        Assertions.assertFalse(employee.getEducations().isEmpty());
        Assertions.assertNotNull(employee.getEducations().stream()
                .findFirst()
                .get()
                .getEducationId());

    }

    @Test
    void findEmployeeById() {
        Optional<Employee> employee = employeeRepository.findById(1);

        Assertions.assertFalse(employee.isEmpty());
        Assertions.assertEquals(1, employee.get().getEmployeeId().intValue());
    }

    @Test
    void editEmployee() {
        Employee employee = employeeRepository.findById(1).get();

        String newName = "New Name";

        employee.setName(newName);

        employeeRepository.saveAndFlush(employee);

        Assertions.assertEquals(1, employee.getEmployeeId().intValue());
        Assertions.assertEquals(newName, employee.getName());
    }

    @Test
    void deleteEmployeeById() {
        employeeRepository.deleteById(1);

        Optional<Employee> employee = employeeRepository.findById(1);

        Assertions.assertTrue(employee.isEmpty());
    }

    @Test
    void tryAddEmployeeWithExistentEmail() {
        Employee employee = getEmployee();
        employee.setEmail("lev@mail.ru");

        Assertions.assertThrows(DataIntegrityViolationException.class,
                () -> employeeRepository.saveAndFlush(employee));
    }

    @Test
    void tryAddEmployeeWithInvalidParams() {
        Employee employee = getEmployee();
        employee.setAddress(null);

        Assertions.assertThrows(ConstraintViolationException.class,
                () -> employeeRepository.saveAndFlush(employee));
    }

    private Employee getEmployee() {
        Employee employee = Employee.builder()
                .name("New Employee")
                .birthDate(LocalDate.parse("1997-11-11"))
                .email("new@email.com")
                .hireDate(LocalDate.now())
                .phoneNumber("364987654315")
                .passportIdentificationNumber("987987654987654dda6547")
                .position("Director")
                .department(Department.builder()
                        .departmentId(1)
                        .build()
                )
                .salary(12345)
                .educations(new HashSet<>())
                .address(getAddress())
                .build();

        employee.addEducation(getEducation());

        return employee;
    }

    private Address getAddress() {
        return Address.builder()
                .country("test")
                .city("test")
                .street("test")
                .houseNumber("test")
                .apartmentNumber("test")
                .postalCode("test")
                .build();
    }

    private Education getEducation() {
        return Education.builder()
                .schoolName("Harvard University")
                .degree("Specialist")
                .fieldOfStudy("Computer Science")
                .startYear("1995")
                .endYear("2009")
                .build();
    }
}
