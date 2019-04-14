package com.moro.web.rest;

import com.moro.model.entity.audit.EmployeeAudit;
import com.moro.service.EmployeeAuditService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/employee")
@Slf4j
public class EmployeeAuditRestController {

    private final EmployeeAuditService employeeAuditService;

    @Autowired
    public EmployeeAuditRestController(final EmployeeAuditService employeeAuditService) {
        this.employeeAuditService = employeeAuditService;
    }

    @GetMapping(value = "/audit")
    public ResponseEntity<List<EmployeeAudit>> findAll() {
        return new ResponseEntity<>(employeeAuditService.findAll(), HttpStatus.FOUND);
    }

    @GetMapping(value = "/audit/{auditId}")
    public ResponseEntity<EmployeeAudit> findById(@PathVariable final int auditId) {
        return new ResponseEntity<>(employeeAuditService.findById(auditId), HttpStatus.FOUND);
    }

    @GetMapping(value = "/{employeeId}/audit")
    public ResponseEntity<List<EmployeeAudit>> findAllByEmployeeId(@PathVariable
                                                                       final int employeeId) {
        return new ResponseEntity<>(
                employeeAuditService.findAllByEmployeeId(employeeId),
                HttpStatus.FOUND
        );
    }
}
