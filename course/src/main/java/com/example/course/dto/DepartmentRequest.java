package com.example.course.dto;

import javax.validation.constraints.NotNull;

public class DepartmentRequest {
    @NotNull
    private String departmentName;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
