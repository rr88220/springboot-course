package com.example.course.controller;

import com.example.course.dto.QueryParam;
import com.example.course.dto.StudentRequest;
import com.example.course.model.Student;
import com.example.course.service.StudentService;
import com.example.course.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/student/{studentId}")
    public ResponseEntity<Student> getStudentById(@PathVariable String studentId){
        Student student = studentService.getStudentById(studentId);
        if(student!=null)
            return ResponseEntity.ok(student);
        else
            return ResponseEntity.notFound().build();
    }

    @Validated
    @GetMapping("/students")
    public ResponseEntity<List<Student>> getStudents(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "create_time") String orderby,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset){
        QueryParam queryParam = new QueryParam();
        queryParam.setDepartment(department);
        queryParam.setSearch(search);
        queryParam.setOrderby(orderby);
        queryParam.setSort(sort);
        queryParam.setLimit(limit);
        queryParam.setOffset(offset);
        List<Student> studentList = studentService.getStudents(queryParam);
        return ResponseEntity.ok(studentList);
    }

    @Validated
    @GetMapping("/students/page")
    public ResponseEntity<Page<Student>> getStudentsPage(
            @RequestParam(required = false) String department,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "create_time") String orderby,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset){
        QueryParam queryParam = new QueryParam();
        queryParam.setDepartment(department);
        queryParam.setSearch(search);
        queryParam.setOrderby(orderby);
        queryParam.setSort(sort);
        queryParam.setLimit(limit);
        queryParam.setOffset(offset);
        List<Student> studentList = studentService.getStudents(queryParam);
        Integer total = studentService.getStudentsTotal(queryParam);

        Page<Student> page = new Page<>();
        page.setLimit(queryParam.getLimit());
        page.setOffset(queryParam.getOffset());
        page.setTotal(total);
        page.setResult(studentList);

        return ResponseEntity.ok(page);
    }

    @PostMapping("/student")
    public ResponseEntity<Student> createStudent(@RequestBody StudentRequest studentRequest){
        String id = studentService.createStudent(studentRequest);
        Student student = studentService.getStudentById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(student);
    }

    @PutMapping("/student/{studentId}")
    public ResponseEntity updateStudent(@PathVariable String studentId,
                                        @RequestBody StudentRequest studentRequest){
        Student student = studentService.getStudentById(studentId);
        if(student!=null){
            studentService.updateStudent(studentId,studentRequest);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/student/{studentId}")
    public ResponseEntity deleteStudent(@PathVariable String studentId){
        studentService.deleteStudent(studentId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }
}
