package com.example.course.dto;

import com.example.course.constant.CourseWeek;

import javax.validation.constraints.NotNull;

public class CourseRequest {

    private String courseId;
    @NotNull
    private String courseName;
    @NotNull
    private Integer point;
    @NotNull
    private Integer teacherId;
    @NotNull
    private Integer stuLimit;
    @NotNull
    private CourseWeek week;
    @NotNull
    private String _class;
    @NotNull
    private String description;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getStuLimit() {
        return stuLimit;
    }

    public void setStuLimit(Integer stuLimit) {
        this.stuLimit = stuLimit;
    }

    public CourseWeek getWeek() {
        return week;
    }

    public void setWeek(CourseWeek week) {
        this.week = week;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
