package com.example.course.service.impl;

import com.example.course.dao.DepartmentDao;
import com.example.course.dto.QueryParam;
import com.example.course.dto.DepartmentRequest;
import com.example.course.model.Department;
import com.example.course.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Department getDepartmentById(Integer departmentId) {
        return departmentDao.getDepartmentById(departmentId);
    }

    @Override
    public List<Department> getDepartments(QueryParam departmentQueryParam) {
        return departmentDao.getDepartments(departmentQueryParam);
    }

    @Override
    public Integer createDepartment(DepartmentRequest departmentRequest) {
        return departmentDao.createDepartment(departmentRequest);
    }

    @Override
    public void updateDepartment(Integer departmentId,DepartmentRequest departmentRequest) {
        departmentDao.updateDepartment(departmentId,departmentRequest);
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        departmentDao.deleteDepartment(departmentId);
    }

    @Override
    public Integer getDepartmentsTotal(QueryParam departmentQueryParam) {
        return departmentDao.getDepartmentsTotal(departmentQueryParam);
    }
}
