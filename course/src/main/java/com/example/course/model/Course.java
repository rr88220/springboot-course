package com.example.course.model;

import com.example.course.constant.CourseWeek;

import java.util.Date;

public class Course {
    private String courseId;
    private String courseName;
    private Integer point;
    private String teacherName;
    private Integer stuLimit;
    private CourseWeek week;
    private String _class;
    private String description;
    private Date createTime;
    private Date lastModifiedTime;

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

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getLastModifiedTime() {
        return lastModifiedTime;
    }

    public void setLastModifiedTime(Date lastModifiedTime) {
        this.lastModifiedTime = lastModifiedTime;
    }
}
