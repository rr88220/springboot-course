package com.example.course.dao;

import com.example.course.dto.QueryParam;
import com.example.course.dto.DepartmentRequest;
import com.example.course.model.Department;

import java.util.List;

public interface DepartmentDao {
    public Department getDepartmentById(Integer departmentId);
    public List<Department> getDepartments(QueryParam departmentQueryParam);
    public Integer createDepartment(DepartmentRequest departmentRequest);
    public void updateDepartment(Integer departmentId,DepartmentRequest departmentRequest);
    public void deleteDepartment(Integer departmentId);
    public Integer getDepartmentsTotal(QueryParam departmentQueryParam);
}
