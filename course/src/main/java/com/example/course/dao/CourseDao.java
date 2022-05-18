package com.example.course.dao;

import com.example.course.dto.CourseRequest;
import com.example.course.dto.QueryParam;
import com.example.course.model.Course;

import java.util.List;

public interface CourseDao {
    public Course getCourseById(String courseId);
    List<Course> getCourses(QueryParam queryParam);
    Integer getCoursesPage(QueryParam queryParam);
    String createCourse(CourseRequest courseRequest);
    void updateCourse(String courseId, CourseRequest courseRequest);
    void deleteCourse(String courseId);
}
