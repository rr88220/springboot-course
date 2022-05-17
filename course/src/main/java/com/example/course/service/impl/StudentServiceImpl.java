package com.example.course.service.impl;

import com.example.course.dao.StudentDao;
import com.example.course.dto.QueryParam;
import com.example.course.dto.StudentRequest;
import com.example.course.model.Student;
import com.example.course.service.StudentService;
import com.example.course.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentDao studentDao;
    @Override
    public Student getStudentById(String studentId) {
        return studentDao.getStudentById(studentId);
    }

    @Override
    public List<Student> getStudents(QueryParam queryParam) {
        return studentDao.getStudents(queryParam);
    }

    @Override
    public Integer getStudentsTotal(QueryParam queryParam) {
        return studentDao.getStudentsTotal(queryParam);
    }

    @Override
    public String createStudent(StudentRequest studentRequest) {
        return studentDao.createStudent(studentRequest);
    }

    @Override
    public void updateStudent(String studentId,StudentRequest studentRequest) {
        studentDao.updateStudent(studentId,studentRequest);
    }

    @Override
    public void deleteStudent(String studentId) {
        studentDao.deleteStudent(studentId);
    }
}
