package com.example.course.service;

import com.example.course.dto.CourseRequest;
import com.example.course.dto.QueryParam;
import com.example.course.model.Course;

import java.util.List;

public interface CourseService {
    public Course getCourseById(String courseId);
    List<Course> getCourses(QueryParam queryParam);
    Integer getCoursesTotal(QueryParam queryParam);
    String createCourse(CourseRequest courseRequest);
    void updateCourse(String courseId, CourseRequest courseRequest);
    void deleteCourse(String courseId);
}
