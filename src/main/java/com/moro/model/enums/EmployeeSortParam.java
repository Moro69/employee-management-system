package com.moro.model.enums;

public enum EmployeeSortParam {
    BIRTH_DATE("birth_date"),
    NAME("name"),
    SALARY("salary");

    private String sortParam;

    EmployeeSortParam(final String sortParam) {
        this.sortParam = sortParam;
    }

    public String getSortParam() {
        return sortParam;
    }
}
