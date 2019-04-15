package com.moro.service;

import com.moro.model.entity.Employee;
import com.moro.model.entity.audit.EmployeeAudit;

import java.util.List;

public interface EmployeeAuditService {

    EmployeeAudit save(Employee employee);

    List<EmployeeAudit> findAll();

    EmployeeAudit findById(int id);

    List<EmployeeAudit> findAllByEmployeeId(int employeeId);
}
