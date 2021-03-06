package com.example.course.controller;

import com.example.course.constant.DepartmentCategory;
import com.example.course.dto.QueryParam;
import com.example.course.dto.DepartmentRequest;
import com.example.course.model.Department;
import com.example.course.service.DepartmentService;
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
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/department/{departmentId}")
    public ResponseEntity<Department> getDepartmentById(@PathVariable Integer departmentId){
        Department department = departmentService.getDepartmentById(departmentId);
        if(department != null)
            return ResponseEntity.status(HttpStatus.OK).body(department);
        else
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getDepartments(
            @RequestParam(required = false) DepartmentCategory category,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "department_id") String orderby,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset){
        QueryParam queryParam = new QueryParam();
        queryParam.setCategory(category);
        queryParam.setSearch(search);
        queryParam.setOrderby(orderby);
        queryParam.setSort(sort);
        queryParam.setLimit(limit);
        queryParam.setOffset(offset);

        List<Department> departmentList = departmentService.getDepartments(queryParam);
        return ResponseEntity.ok(departmentList);
    }

    @GetMapping("/departments/page")
    public ResponseEntity<Page<Department>> getDepartmentsPage(
            @RequestParam(required = false) DepartmentCategory category,
            @RequestParam(required = false) String search,
            @RequestParam(defaultValue = "department_id") String orderby,
            @RequestParam(defaultValue = "ASC") String sort,
            @RequestParam(defaultValue = "5") @Max(1000) @Min(0) Integer limit,
            @RequestParam(defaultValue = "0") @Min(0) Integer offset){
        QueryParam queryParam = new QueryParam();
        queryParam.setCategory(category);
        queryParam.setSearch(search);
        queryParam.setOrderby(orderby);
        queryParam.setSort(sort);
        queryParam.setLimit(limit);
        queryParam.setOffset(offset);

        List<Department> departmentList = departmentService.getDepartments(queryParam);
        Integer total = departmentService.getDepartmentsTotal(queryParam);

        Page<Department> page = new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setResult(departmentList);
        page.setTotal(total);

        return ResponseEntity.ok(page);
    }

    @PostMapping("/department")
    public ResponseEntity<Department> createDepartment(@RequestBody @Valid DepartmentRequest departmentRequest){
        Integer id = departmentService.createDepartment(departmentRequest);
        Department department = departmentService.getDepartmentById(id);
        return ResponseEntity.status(HttpStatus.CREATED).body(department);
    }

    @PutMapping("/department/{departmentId}")
    public ResponseEntity updateDepartment(
            @PathVariable Integer departmentId,
            @RequestBody @Valid DepartmentRequest departmentRequest){
        if(departmentService.getDepartmentById(departmentId) != null){
            departmentService.updateDepartment(departmentId,departmentRequest);
            return ResponseEntity.accepted().build();
        }else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/department/{departmentId}")
    public ResponseEntity deleteDepartment(@PathVariable Integer departmentId){
        departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok().build();
    }


}
