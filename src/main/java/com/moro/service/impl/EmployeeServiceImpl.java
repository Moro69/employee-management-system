package com.moro.service.impl;

import com.moro.dao.repository.EmployeeRepository;
import com.moro.model.entity.Employee;
import com.moro.model.exception.EntityNotFoundException;
import com.moro.model.search.EmployeeFacetedSearch;
import com.moro.service.EmployeeService;
import com.moro.service.validator.EmployeeValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeServiceImpl(final EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Employee findEmployeeById(final Integer employeeId) {
        log.info("Retrieving employee by id {}", employeeId);

        return tryFindEmployeeById(employeeId);
    }

    @Override
    public Employee updateEmployee(final Employee employee) {
        log.info("Updating employee with id {}", employee.getEmployeeId());

        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public Employee addEmployee(final Employee employee) {
        log.info("Adding new employee");

        return employeeRepository.saveAndFlush(employee);
    }

    @Override
    public void deleteEmployeeById(final Integer employeeId) {
        log.info("Deleting employee by id {}", employeeId);

        employeeRepository.delete(tryFindEmployeeById(employeeId));
    }

    @Override
    public Page<Employee> findAllEmployees(final EmployeeFacetedSearch params) {
        log.info("Finding employees {}, {}", params);

        EmployeeValidator.validateFacetedSearchParams(params);

        Pageable pageable = createPageRequest(params);

        return employeeRepository.findAll(params.getDepartmentId(),
                params.getEmployeeName(),
                params.getCountry(),
                params.getFromSalary(),
                params.getToSalary(),
                pageable);
    }

    private Pageable createPageRequest(final EmployeeFacetedSearch params) {
        return PageRequest.of(params.getPage(),
                params.getPageSize(),
                params.getSortDirection(),
                params.getSortParam().getSortParam());
    }

    private Employee tryFindEmployeeById(final Integer employeeId) {
        return employeeRepository.findById(employeeId).orElseThrow(
                () -> new EntityNotFoundException(
                        String.format("No employee with id %d", employeeId))
        );
    }
}
