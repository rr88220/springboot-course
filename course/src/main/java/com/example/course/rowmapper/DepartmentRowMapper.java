package com.example.course.rowmapper;

import com.example.course.constant.DepartmentCategory;
import com.example.course.model.Department;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DepartmentRowMapper implements RowMapper<Department> {
    @Override
    public Department mapRow(ResultSet resultSet, int i) throws SQLException {
        Department department = new Department();
        department.setDepartmentId(resultSet.getInt("department_id"));
        department.setCategory(DepartmentCategory.valueOf(resultSet.getString("category")));
        department.setDepartmentName(resultSet.getString("department_name"));
        return department;
    }
}
