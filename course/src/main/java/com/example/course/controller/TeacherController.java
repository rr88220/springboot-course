package com.example.course.controller;

import com.example.course.dto.QueryParam;
import com.example.course.dto.TeacherRequest;
import com.example.course.model.Teacher;
import com.example.course.service.TeacherService;
import com.example.course.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/teacher/{teacherId}")
    public ResponseEntity<Teacher> getTeacherById(@PathVariable Integer teacherId){
        Teacher teacher = teacherService.getTeacherById(teacherId);
        if(teacher != null){
            return ResponseEntity.ok(teacher);
        }else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/teachers")
    public ResponseEntity<List<Teacher>> getTeachers(@RequestParam(required = false) String department,
                                                     @RequestParam(required = false) String search,
                                                     @RequestParam(defaultValue = "create_time") String orderby,
                                                     @RequestParam(defaultValue = "desc") String sort,
                                                     @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                                                     @RequestParam(defaultValue = "0") @Min(0) Integer offset){
        QueryParam queryParam = new QueryParam();
        queryParam.setDepartment(department);
        queryParam.setSearch(search);
        queryParam.setOrderby(orderby);
        queryParam.setSort(sort);
        queryParam.setLimit(limit);
        queryParam.setOffset(offset);

        List<Teacher> teacherList = teacherService.getTeachers(queryParam);
        return ResponseEntity.ok(teacherList);
    }

    @GetMapping("/teachers/page")
    public ResponseEntity<Page<Teacher>> getTeachersTotal(@RequestParam(required = false) String department,
                                                          @RequestParam(required = false) String search,
                                                          @RequestParam(defaultValue = "create_time") String orderby,
                                                          @RequestParam(defaultValue = "desc") String sort,
                                                          @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
                                                          @RequestParam(defaultValue = "0") @Min(0) Integer offset){
        QueryParam queryParam = new QueryParam();
        queryParam.setDepartment(department);
        queryParam.setSearch(search);
        queryParam.setOrderby(orderby);
        queryParam.setSort(sort);
        queryParam.setLimit(limit);
        queryParam.setOffset(offset);

        List<Teacher> teacherList = teacherService.getTeachers(queryParam);
        Integer total = teacherService.getTeachersTotal(queryParam);

        Page<Teacher> page = new Page<>();
        page.setLimit(queryParam.getLimit());
        page.setOffset(queryParam.getOffset());
        page.setTotal(total);
        page.setResult(teacherList);

        return ResponseEntity.ok(page);
    }
    @PostMapping("/teacher")
    public ResponseEntity<Teacher> createTeacher(@RequestBody @Valid TeacherRequest teacherRequest){
        Integer id = teacherService.createTeacher(teacherRequest);
        Teacher teacher = teacherService.getTeacherById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(teacher);
    }

    @PutMapping("/teacher/{teacherId}")
    public ResponseEntity updateTeacher(@PathVariable Integer teacherId, @RequestBody @Valid TeacherRequest teacherRequest){
        if(teacherService.getTeacherById(teacherId)!=null) {
            teacherService.updateTeacher(teacherId, teacherRequest);
            return ResponseEntity.accepted().build();
        }else{
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/teacher/{teacherId}")
    public ResponseEntity deleteTeacher(@PathVariable Integer teacherId){
        teacherService.deleteTeacher(teacherId);
        return ResponseEntity.ok().build();
    }


}
