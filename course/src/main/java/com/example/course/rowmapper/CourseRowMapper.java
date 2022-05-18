package com.example.course.rowmapper;

import com.example.course.constant.CourseWeek;
import com.example.course.model.Course;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CourseRowMapper implements RowMapper<Course> {
    @Override
    public Course mapRow(ResultSet resultSet, int i) throws SQLException {
        Course course = new Course();
        course.setCourseId(resultSet.getString("course_id"));
        course.setCourseName(resultSet.getString("course_name"));
        course.setPoint(resultSet.getInt("point"));
        course.setTeacherName(resultSet.getString("teacher_name"));
        course.setStuLimit(resultSet.getInt("stu_limit"));
        course.setWeek(CourseWeek.valueOf(resultSet.getString("week")));
        course.set_class(resultSet.getString("class"));
        course.setDescription(resultSet.getString("description"));
        course.setCreateTime(resultSet.getTimestamp("create_time"));
        course.setLastModifiedTime(resultSet.getTimestamp("last_modified_time"));
        return course;
    }
}
