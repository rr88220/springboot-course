package com.example.course.dto;

import com.example.course.constant.DepartmentCategory;

import javax.validation.constraints.NotNull;

public class DepartmentRequest {
    @NotNull
    private DepartmentCategory category;
    @NotNull
    private String departmentName;

    public DepartmentCategory getCategory() {
        return category;
    }

    public void setCategory(DepartmentCategory category) {
        this.category = category;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
