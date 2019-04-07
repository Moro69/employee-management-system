package com.moro.service.validator;

import com.moro.model.search.DateRange;
import com.moro.model.search.DepartmentFacetedSearch;

import java.time.LocalDate;

public class DepartmentValidator {

    private static final LocalDate MIN_DATE = LocalDate.of(1901, 1, 1);
    private static final LocalDate MAX_DATE = LocalDate.of(3500, 1, 1);

    public static void validateFacetedSearchParams(final DepartmentFacetedSearch params) {
        checkDates(params);

        checkSort(params);

        checkPage(params);
    }

    private static void checkPage(final DepartmentFacetedSearch params) {
        if (params.getPageSize() == null) {
            params.setPageSize(3);
        }

        if (params.getPage() == null) {
            params.setPage(0);
        }
    }

    private static void checkSort(final DepartmentFacetedSearch params) {
        if (params.getSortDirection() == null) {
            params.setSortDirection("asc");
        }

        if (params.getSortParam() == null) {
            params.setSortParam("name");
        }
    }

    private static void checkDates(final DepartmentFacetedSearch params) {
        if (params.getDateRange() == null) {
            params.setDateRange(DateRange.builder()
                    .fromDate(MIN_DATE)
                    .toDate(MAX_DATE)
                    .build());
        }

        if (params.getDateRange().getFromDate() == null) {
            params.getDateRange().setFromDate(MIN_DATE);
        }

        if (params.getDateRange().getToDate() == null) {
            params.getDateRange().setToDate(MAX_DATE);
        }

        if (params.getDateRange().getFromDate()
                .isAfter(params.getDateRange().getToDate())) {
            throw new RuntimeException("Invalid date range.");
        }
    }
}
