package com.example.course.dao.impl;

import com.example.course.dao.StudentDao;
import com.example.course.dto.QueryParam;
import com.example.course.dto.StudentRequest;
import com.example.course.model.Student;
import com.example.course.rowmapper.StudentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class StudentDaoImpl implements StudentDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    @Override
    public Student getStudentById(String studentId) {
        String sql = "SELECT student_id,student_name,department_name,password,create_time,last_modified_time FROM student INNER JOIN department USING(department_id) WHERE student_id = :studentId";
        Map<String,Object> map = new HashMap<>();
        map.put("studentId",studentId);
        List<Student> studentList = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());
        if(studentList.size()>0)
            return studentList.get(0);
        else
            return null;
    }

    @Override
    public List<Student> getStudents(QueryParam queryParam) {
        String sql = "SELECT student_id,student_name,department_name,password,create_time,last_modified_time FROM student INNER JOIN department USING(department_id) WHERE 1=1";
        Map<String,Object> map = new HashMap<>();
        if(queryParam.getDepartment()!=null){
            sql = sql + " AND department_name = :department";
            map.put("department",queryParam.getDepartment());
        }
        if(queryParam.getSearch()!=null){
            sql = sql + " AND student_name LIKE :search";
            map.put("search","%"+queryParam.getSearch()+"%");
        }
        sql = sql + " ORDER BY "+queryParam.getOrderby() + " " + queryParam.getSort();
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit",queryParam.getLimit());
        map.put("offset",queryParam.getOffset());
        List<Student> studentList = namedParameterJdbcTemplate.query(sql, map, new StudentRowMapper());
        return studentList;
    }

    @Override
    public Integer getStudentsTotal(QueryParam queryParam) {
        String sql = "SELECT COUNT(*) FROM student INNER JOIN department USING(department_id) WHERE 1=1";
        Map<String,Object> map = new HashMap<>();
        if(queryParam.getDepartment()!=null){
            sql = sql + " AND department_name = :department";
            map.put("department",queryParam.getDepartment());
        }
        if(queryParam.getSearch()!=null){
            sql = sql + " AND student_name LIKE :search";
            map.put("search","%"+queryParam.getSearch()+"%");
        }
        sql = sql + " ORDER BY "+queryParam.getOrderby() + " " + queryParam.getSort();
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit",queryParam.getLimit());
        map.put("offset",queryParam.getOffset());
        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);
        return total;
    }

    @Override
    public String createStudent(StudentRequest studentRequest) {
        String sql = "INSERT INTO student(student_id,student_name,department_id,create_time,last_modified_time) VALUES (:studentId , :studentName , :departmentId ,:createTime,:lastModifiedTime)";
        Map<String,Object> map = new HashMap<>();
        map.put("studentId",studentRequest.getStudentId());
        map.put("studentName",studentRequest.getStudentName());
        map.put("departmentId",studentRequest.getDepartmentId());
        Date now = new Date();
        map.put("createTime",now);
        map.put("lastModifiedTime",now);
        namedParameterJdbcTemplate.update(sql,map);
        return studentRequest.getStudentId();
    }

    @Override
    public void updateStudent(String studentId,StudentRequest studentRequest) {
        String sql = "UPDATE student SET student_name = :studentName , department_id = :departmentId , password = :password ,last_modified_time = :lastModifiedTime WHERE student_id = :studentId";
        Map<String,Object> map = new HashMap<>();
        map.put("studentName",studentRequest.getStudentName());
        map.put("departmentId",studentRequest.getDepartmentId());
        map.put("password",studentRequest.getPassword());
        map.put("studentId",studentId);
        Date now = new Date();
        map.put("lastModifiedTime",now);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteStudent(String studentId) {
        String sql = "DELETE FROM student WHERE student_id = :studentId";
        Map<String,Object> map = new HashMap<>();
        map.put("studentId",studentId);
        namedParameterJdbcTemplate.update(sql,map);
    }

}
