package com.example.course.service;

import com.example.course.dto.QueryParam;
import com.example.course.dto.StudentRequest;
import com.example.course.model.Student;
import com.example.course.util.Page;

import java.util.List;

public interface StudentService {
    public Student getStudentById(String studentId);
    public List<Student> getStudents(QueryParam queryParam);
    public Integer getStudentsTotal(QueryParam queryParam);
    public String createStudent (StudentRequest studentRequest);
    public void updateStudent(String studentId,StudentRequest studentRequest);
    public void deleteStudent(String studentId);
}
