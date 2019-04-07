package com.moro.service;

import com.moro.model.entity.Employee;
import com.moro.model.search.EmployeeFacetedSearch;
import org.springframework.data.domain.Page;

public interface EmployeeService {

    Employee findEmployeeById(Integer employeeId);

    Employee updateEmployee(Employee employee);

    Employee addEmployee(Employee employee);

    void deleteEmployeeById(Integer employeeId);

    Page<Employee> findAllEmployees(EmployeeFacetedSearch params);
}
