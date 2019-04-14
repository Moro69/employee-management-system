package com.moro.service.impl;

import com.moro.dao.repository.EmployeeAuditRepository;
import com.moro.model.entity.Employee;
import com.moro.model.entity.audit.EmployeeAudit;
import com.moro.model.exception.EntityNotFoundException;
import com.moro.service.EmployeeAuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@Slf4j
public class EmployeeAuditServiceImpl implements EmployeeAuditService {

    private final EmployeeAuditRepository employeeAuditRepository;

    @Autowired
    public EmployeeAuditServiceImpl(final EmployeeAuditRepository employeeAuditRepository) {
        this.employeeAuditRepository = employeeAuditRepository;
    }

    @Override
    public EmployeeAudit save(final Employee employee) {
        return employeeAuditRepository.saveAndFlush(EmployeeAudit.of(employee));
    }

    @Override
    public List<EmployeeAudit> findAll() {
        return employeeAuditRepository.findAll();
    }

    @Override
    public EmployeeAudit findById(final int id) {
        return employeeAuditRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(
                        String.format("No employee audit with id %d", id)
                ));
    }

    @Override
    public List<EmployeeAudit> findAllByEmployeeId(final int employeeId) {
        return employeeAuditRepository.findAllByEmployeeId(employeeId);
    }
}
