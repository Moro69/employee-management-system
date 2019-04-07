package com.moro.service.validator;

import com.moro.model.enums.EmployeeSortParam;
import com.moro.model.search.EmployeeFacetedSearch;
import org.springframework.data.domain.Sort;

public class EmployeeValidator {

    private static final int DEFAULT_PAGE_SIZE = 3;
    private static final int DEFAULT_PAGE = 0;

    public static void validateFacetedSearchParams(final EmployeeFacetedSearch params) {
        checkPage(params);
        checkSort(params);
    }

    private static void checkPage(final EmployeeFacetedSearch params) {
        if (params.getPageSize() == null) {
            params.setPageSize(DEFAULT_PAGE_SIZE);
        }

        if (params.getPage() == null) {
            params.setPage(DEFAULT_PAGE);
        }
    }

    private static void checkSort(final EmployeeFacetedSearch params) {
        if (params.getSortDirection() == null) {
            params.setSortDirection(Sort.Direction.ASC);
        }

        if (params.getSortParam() == null) {
            params.setSortParam(EmployeeSortParam.NAME);
        }
    }
}
