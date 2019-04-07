package com.moro.service.impl;

import com.moro.dao.repository.DepartmentRepository;
import com.moro.model.entity.Department;
import com.moro.model.exception.EntityNotFoundException;
import com.moro.model.search.DepartmentFacetedSearch;
import com.moro.service.DepartmentService;
import com.moro.service.validator.DepartmentValidator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Slf4j
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentServiceImpl(final DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Department findDepartmentById(final Integer departmentId) {
        log.info("Retrieving department by id {}", departmentId);

        return tryFindDepartmentById(departmentId);
    }

    @Override
    public Department updateDepartment(final Department department) {
        log.info("Updating department");

        return departmentRepository.saveAndFlush(department);
    }

    @Override
    public Department addDepartment(final Department department) {
        log.info("Add new department");

        return departmentRepository.saveAndFlush(department);
    }

    @Override
    public void deleteDepartmentById(final Integer departmentId) {
        log.info("Delete department by id {}", departmentId);

        Department department = tryFindDepartmentById(departmentId);
        Department defaultDepartment =
                departmentRepository.findByName("Default")
                        .orElseThrow(() -> new EntityNotFoundException("Cannot delete department. "
                                + "There is no default department for employees.")
        );

        department.getEmployees().forEach(employee -> employee.setDepartment(defaultDepartment));
        department.getEmployees().clear();

        departmentRepository.delete(department);
    }

    @Override
    public Page<Department> findAllDepartments(final DepartmentFacetedSearch params) {
        log.info("Find all departments {}", params);

        DepartmentValidator.validateFacetedSearchParams(params);

        return departmentRepository
                .findAllByFormationDateBetween(params.getDateRange().getFromDate(),
                        params.getDateRange().getToDate(),
                        createPageRequest(params));
    }

    private Pageable createPageRequest(final DepartmentFacetedSearch params) {
        return PageRequest.of(
                params.getPage(),
                params.getPageSize(),
                Sort.Direction.fromString(params.getSortDirection()),
                params.getSortParam());
    }

    private Department tryFindDepartmentById(final int id) {
        return departmentRepository
                .findById(id)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                String.format("No department with id %d", id))
                );
    }
}
