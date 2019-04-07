package com.moro.model.search;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DepartmentFacetedSearch {

    private DateRange dateRange;

    @PositiveOrZero
    private Integer page;

    @PositiveOrZero
    private Integer pageSize;

    private String sortDirection;

    private String sortParam;
}
