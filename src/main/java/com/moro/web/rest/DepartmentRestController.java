package com.moro.web.rest;

import com.moro.model.entity.Department;
import com.moro.model.search.DepartmentFacetedSearch;
import com.moro.service.DepartmentService;
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
@RequestMapping(value = "/department")
@Slf4j
public class DepartmentRestController {

    private DepartmentService departmentService;

    @Autowired
    public DepartmentRestController(final DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping(value = "/{departmentId}")
    public ResponseEntity<Department> findDepartmentById(@PathVariable
                                                             final Integer departmentId) {
        log.info("Retrieving department by id {}", departmentId);

        return new ResponseEntity<>(
                departmentService.findDepartmentById(departmentId),
                HttpStatus.FOUND
        );
    }

    @PutMapping
    public ResponseEntity<Department> updateDepartment(@RequestBody
                                                           @Valid final Department department) {
        log.info("Updating department");

        return new ResponseEntity<>(
                departmentService.updateDepartment(department),
                HttpStatus.OK
        );
    }

    @PostMapping
    public ResponseEntity<Department> addDepartment(@RequestBody
                                                        @Valid final Department department) {
        log.info("Adding new department");

        return new ResponseEntity<>(
                departmentService.addDepartment(department),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping(value = "/{departmentId}")
    public ResponseEntity deleteDepartmentById(@PathVariable final Integer departmentId) {
        log.info("Deleting department by id {}", departmentId);

        departmentService.deleteDepartmentById(departmentId);

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<Page<Department>>
    findAllDepartments(@Valid final DepartmentFacetedSearch params) {
        log.info("Finding departments: {}", params);

        return new ResponseEntity<>(departmentService.findAllDepartments(params),
                HttpStatus.FOUND);
    }
}
