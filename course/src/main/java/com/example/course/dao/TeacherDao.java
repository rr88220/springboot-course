package com.example.course.dao;

import com.example.course.dto.DepartmentQueryParam;
import com.example.course.dto.DepartmentRequest;
import com.example.course.dto.TeacherQueryParam;
import com.example.course.dto.TeacherRequest;
import com.example.course.model.Department;
import com.example.course.model.Teacher;

import java.util.List;

public interface TeacherDao {
    public Teacher getTeacherById(Integer teacherId);
    public List<Teacher> getTeachers(TeacherQueryParam teacherQueryParam);
    public Integer createTeacher(TeacherRequest teacherRequest);
    public void updateTeacher(Integer teacherId,TeacherRequest teacherRequest);
    public void deleteTeacher(Integer teacherId);
    public Integer getTeachersTotal(TeacherQueryParam teacherQueryParam);
}
