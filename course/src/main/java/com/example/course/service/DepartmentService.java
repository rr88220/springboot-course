package com.example.course.service;

import com.example.course.dto.DepartmentQueryParam;
import com.example.course.dto.DepartmentRequest;
import com.example.course.model.Department;

import java.util.List;

public interface DepartmentService {
    public Department getDepartmentById(Integer departmentId);
    public List<Department> getDepartments(DepartmentQueryParam departmentQueryParam);
    public Integer createDepartment(DepartmentRequest departmentRequest);
    public void updateDepartment(Integer departmentId,DepartmentRequest departmentRequest);
    public void deleteDepartment(Integer departmentId);
    public Integer getDepartmentsTotal(DepartmentQueryParam departmentQueryParam);
}
