package com.moro.service;

import com.moro.model.entity.Department;
import com.moro.model.search.DepartmentFacetedSearch;
import org.springframework.data.domain.Page;

public interface DepartmentService {

    Department findDepartmentById(Integer departmentId);

    Department updateDepartment(Department department);

    Department addDepartment(Department department);

    void deleteDepartmentById(Integer departmentId);

    Page<Department> findAllDepartments(DepartmentFacetedSearch params);
}
