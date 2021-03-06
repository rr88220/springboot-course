package com.example.course.service;

import com.example.course.dto.QueryParam;
import com.example.course.dto.TeacherRequest;
import com.example.course.model.Teacher;

import java.util.List;

public interface TeacherService {
    public Teacher getTeacherById(Integer teacherId);
    public List<Teacher> getTeachers(QueryParam queryParam);
    public Integer getTeachersTotal(QueryParam queryParam);
    public Integer createTeacher(TeacherRequest teacherRequest);
    public void updateTeacher(Integer teacherId,TeacherRequest teacherRequest);
    public void deleteTeacher(Integer teacherId);
}
