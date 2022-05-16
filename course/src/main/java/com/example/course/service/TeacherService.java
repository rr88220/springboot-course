package com.example.course.service;

import com.example.course.dto.TeacherQueryParam;
import com.example.course.dto.TeacherRequest;
import com.example.course.model.Teacher;

import java.util.List;

public interface TeacherService {
    public Teacher getTeacherById(Integer teacherId);
    public List<Teacher> getTeachers(TeacherQueryParam teacherQueryParam);
    public Integer createTeacher(TeacherRequest teacherRequest);
    public void updateTeacher(Integer teacherId,TeacherRequest teacherRequest);
    public void deleteTeacher(Integer teacherId);
    public Integer getTeachersTotal(TeacherQueryParam teacherQueryParam);
}
