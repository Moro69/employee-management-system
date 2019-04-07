package com.moro.dao.repository;

import com.moro.model.entity.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    Page<Department> findAllByFormationDateBetween(LocalDate fromDate,
                                                   LocalDate toDate,
                                                   Pageable pageable);
    Optional<Department> findByName(String name);
}
