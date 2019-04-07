package com.moro.dao.repository;

import com.moro.model.entity.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    @Query(name = "Employee.findAll", nativeQuery = true)
    Page<Employee> findAll(Integer departmentId,
                           String name,
                           String country,
                           Integer fromSalary,
                           Integer toSalary,
                           Pageable pageable);
}
