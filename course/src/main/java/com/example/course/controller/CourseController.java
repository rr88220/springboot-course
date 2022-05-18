package com.example.course.controller;

import com.example.course.constant.CourseWeek;
import com.example.course.dto.CourseRequest;
import com.example.course.dto.DepartmentRequest;
import com.example.course.dto.QueryParam;
import com.example.course.model.Course;
import com.example.course.service.CourseService;
import com.example.course.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@RestController
public class CourseController {

    @Autowired
    CourseService courseService;

    @GetMapping("/course/{courseId}")
    public ResponseEntity<Course> getCourseById(@PathVariable String courseId){
        Course course = courseService.getCourseById(courseId);
        if(course != null){
            return ResponseEntity.ok(course);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @Validated
    @GetMapping("/courses")
    public ResponseEntity<List<Course>> getCourses(
            @RequestParam(required = false) CourseWeek week,
            @RequestParam(required = false) String _class,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "course_id") String orderby,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset){
        QueryParam queryParam = new QueryParam();
        queryParam.setWeek(week);
        queryParam.set_class(_class);
        queryParam.setSearch(search);
        queryParam.setOrderby(orderby);
        queryParam.setSort(sort);
        queryParam.setLimit(limit);
        queryParam.setOffset(offset);
        List<Course> courseList = courseService.getCourses(queryParam);
        return ResponseEntity.ok().body(courseList);
    }

    @Validated
    @GetMapping("/courses/page")
    public ResponseEntity<Page> getCoursesPage(
            @RequestParam(required = false) CourseWeek week,
            @RequestParam(required = false) String _class,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "course_id") String orderby,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset){
        QueryParam queryParam = new QueryParam();
        queryParam.setWeek(week);
        queryParam.set_class(_class);
        queryParam.setSearch(search);
        queryParam.setOrderby(orderby);
        queryParam.setSort(sort);
        queryParam.setLimit(limit);
        queryParam.setOffset(offset);
        List<Course> courseList = courseService.getCourses(queryParam);
        Integer total = courseService.getCoursesTotal(queryParam);
        Page page = new Page();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResult(courseList);
        return ResponseEntity.ok().body(page);
    }

    @PostMapping("/course")
    public ResponseEntity<Course> createCourse(@RequestBody @Valid CourseRequest courseRequest){
        String courseId = courseService.createCourse(courseRequest);
        Course course = courseService.getCourseById(courseId);
        return ResponseEntity.ok(course);
    }

    @PutMapping("/course/{courseId}")
    public ResponseEntity updateCourse(
            @PathVariable String courseId,
            @RequestBody @Valid CourseRequest courseRequest){
        if(courseService.getCourseById(courseId) != null){
            courseService.updateCourse(courseId,courseRequest);
            return ResponseEntity.accepted().build();
        }else {
            return ResponseEntity.notFound().build();
        }

    }

    @DeleteMapping("/course/{courseId}")
    public ResponseEntity courseDelete(@PathVariable String courseId){
        courseService.deleteCourse(courseId);
        return ResponseEntity.ok().build();
    }
}
