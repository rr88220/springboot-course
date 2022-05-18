package com.example.course.service.impl;

import com.example.course.dao.CourseDao;
import com.example.course.dto.CourseRequest;
import com.example.course.dto.QueryParam;
import com.example.course.model.Course;
import com.example.course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CourseServiceImpl implements CourseService {
    @Autowired
    CourseDao courseDao;

    @Override
    public Course getCourseById(String courseId) {
        return courseDao.getCourseById(courseId);
    }

    @Override
    public List<Course> getCourses(QueryParam queryParam) {
        return courseDao.getCourses(queryParam);
    }

    @Override
    public Integer getCoursesTotal(QueryParam queryParam) {
        return courseDao.getCoursesPage(queryParam);
    }

    @Override
    public String createCourse(CourseRequest courseRequest) {
        return courseDao.createCourse(courseRequest);
    }

    @Override
    public void updateCourse(String courseId, CourseRequest courseRequest) {
        courseDao.updateCourse(courseId,courseRequest);
    }

    @Override
    public void deleteCourse(String courseId) {
        courseDao.deleteCourse(courseId);
    }
}
