package com.example.course.rowmapper;

import com.example.course.model.Teacher;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TeacherDnameRowMapper implements RowMapper<Teacher> {
    Teacher teacher = new Teacher();

    @Override
    public Teacher mapRow(ResultSet resultSet, int i) throws SQLException {
        teacher.setTeacherId(resultSet.getInt("teacher_id"));
        teacher.setTeacherName(resultSet.getString("teacher_name"));
        teacher.setDepartmentId(resultSet.getInt("department_id"));
        teacher.setCreateTime(resultSet.getTimestamp("create_time"));
        teacher.setLastModifiedTime(resultSet.getTimestamp("last_modified_time"));
        teacher.setDepartmentName(resultSet.getString("department_name"));
        return teacher;
    }
}
