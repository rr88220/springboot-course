package com.example.course.service.impl;

import com.example.course.dao.TeacherDao;
import com.example.course.dto.QueryParam;
import com.example.course.dto.TeacherRequest;
import com.example.course.model.Teacher;
import com.example.course.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherDao teacherDao;

    @Override
    public Teacher getTeacherById(Integer teacherId) {
        return teacherDao.getTeacherById(teacherId);
    }

    @Override
    public List<Teacher> getTeachers(QueryParam queryParam) {
        return teacherDao.getTeachers(queryParam);
    }

    @Override
    public Integer createTeacher(TeacherRequest teacherRequest) {
        return teacherDao.createTeacher(teacherRequest);
    }

    @Override
    public void updateTeacher(Integer teacherId, TeacherRequest teacherRequest) {
        teacherDao.updateTeacher(teacherId,teacherRequest);
    }

    @Override
    public void deleteTeacher(Integer teacherId) {
        teacherDao.deleteTeacher(teacherId);
    }

    @Override
    public Integer getTeachersTotal(QueryParam queryParam) {
        return teacherDao.getTeachersTotal(queryParam);
    }
}
