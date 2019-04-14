package com.moro.dao.repository;

import com.moro.model.entity.audit.EmployeeAudit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeAuditRepository extends JpaRepository<EmployeeAudit, Integer> {

    List<EmployeeAudit> findAllByEmployeeId(int employeeId);
}
