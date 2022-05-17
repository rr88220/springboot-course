package com.example.course.rowmapper;

import com.example.course.model.Student;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentRowMapper implements RowMapper<Student> {
    @Override
    public Student mapRow(ResultSet resultSet, int i) throws SQLException {
        Student student = new Student();
        student.setStudentId(resultSet.getString("student_id"));
        student.setStudentName(resultSet.getString("student_name"));
        student.setDepartmentName(resultSet.getString("department_name"));
        student.setPassword(resultSet.getString("password"));
        student.setCreateTime(resultSet.getTimestamp("create_time"));
        student.setLastModifiedTime(resultSet.getTimestamp("last_modified_time"));
        return student;
    }
}
