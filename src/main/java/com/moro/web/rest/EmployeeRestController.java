package com.moro.web.rest;

import com.moro.model.entity.Employee;
import com.moro.model.search.EmployeeFacetedSearch;
import com.moro.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/employee")
@Slf4j
public class EmployeeRestController {

    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(final EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public ResponseEntity<Page<Employee>> findEmployees(@Valid final EmployeeFacetedSearch params) {
        log.info("Finding employees {}", params);

        return new ResponseEntity<>(employeeService.findAllEmployees(params), HttpStatus.FOUND);
    }

    @GetMapping(value = "/{employeeId}")
    public ResponseEntity<Employee> findEmployeeById(@PathVariable final Integer employeeId) {
        log.info("Finding employee by id {}", employeeId);

        return new ResponseEntity<>(employeeService.findEmployeeById(employeeId), HttpStatus.FOUND);
    }

    @PostMapping
    public ResponseEntity<Employee> addEmployee(@RequestBody @Valid final Employee employee) {
        log.info("Add new employee");

        return new ResponseEntity<>(employeeService.addEmployee(employee), HttpStatus.CREATED);
    }

    @PutMapping
    public ResponseEntity<Employee> updateEmployee(@RequestBody @Valid final Employee employee) {
        log.info("Updating employee");

        return ResponseEntity.ok(employeeService.updateEmployee(employee));
    }

    @DeleteMapping(value = "/{employeeId}")
    public ResponseEntity deleteEmployeeById(@PathVariable final Integer employeeId) {
        log.info("Deleting employee by id {}", employeeId);

        employeeService.deleteEmployeeById(employeeId);

        return new ResponseEntity(HttpStatus.OK);
    }
}
