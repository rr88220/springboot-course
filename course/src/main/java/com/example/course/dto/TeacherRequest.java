package com.example.course.dto;

import javax.validation.constraints.NotNull;

public class TeacherRequest {
    @NotNull
    private String teacherName;
    @NotNull
    private Integer departmentId;

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public Integer getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Integer departmentId) {
        this.departmentId = departmentId;
    }
}
