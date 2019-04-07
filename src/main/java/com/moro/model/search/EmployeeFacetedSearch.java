package com.moro.model.search;

import com.moro.model.enums.EmployeeSortParam;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeFacetedSearch {

    private Integer departmentId;

    private String employeeName;

    private String country;

    @PositiveOrZero
    private Integer fromSalary;

    @PositiveOrZero
    private Integer toSalary;

    @PositiveOrZero
    private Integer page;

    @PositiveOrZero
    private Integer pageSize;

    private Sort.Direction sortDirection;

    private EmployeeSortParam sortParam;
}
