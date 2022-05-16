package com.example.course.dao.impl;

import com.example.course.dao.TeacherDao;
import com.example.course.dto.TeacherQueryParam;
import com.example.course.dto.TeacherRequest;
import com.example.course.model.Teacher;
import com.example.course.rowmapper.TeacherDnameRowMapper;
import com.example.course.rowmapper.TeacherRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class TeacherDaoImpl implements TeacherDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Teacher getTeacherById(Integer teacherId) {
        String sql = "SELECT teacher_id,teacher_name,teacher.department_id,department_name,create_time,last_modified_time FROM teacher INNER JOIN department USING(department_id) WHERE teacher_id = :teacherId";
        Map<String,Object> map = new HashMap<>();
        map.put("teacherId",teacherId);

        List<Teacher> teacherList = namedParameterJdbcTemplate.query(sql, map, new TeacherDnameRowMapper());
        if(teacherList.size()>0)
            return teacherList.get(0);
        else
            return null;
    }

    @Override
    public List<Teacher> getTeachers(TeacherQueryParam teacherQueryParam) {
        String sql = "SELECT teacher_id,teacher_name,department_id,create_time,last_modified_time FROM teacher WHERE 1=1";
        Map<String,Object> map = new HashMap<>();

        if(teacherQueryParam.getSearch()!=null){
            sql = sql + " AND teacher_name LIKE :search";
            map.put("search","%"+teacherQueryParam.getSearch()+"%");
        }
        sql = sql +" ORDER BY "+teacherQueryParam.getOrdeyby()+" "+teacherQueryParam.getSort();
        sql = sql +" LIMIT :limit OFFSET :offset";
        map.put("limit",teacherQueryParam.getLimit());
        map.put("offset",teacherQueryParam.getOffset());

        List<Teacher> teacherList = namedParameterJdbcTemplate.query(sql, map, new TeacherRowMapper());
        return teacherList;
    }

    @Override
    public Integer createTeacher(TeacherRequest teacherQueryParam) {
        String sql = "INSERT INTO teacher(teacher_name,department_id,create_time,last_modified_time) VALUES (:teacherName,:departmentId,:createTime,:lastModifiedTime)";
        Map<String,Object> map = new HashMap<>();
        map.put("teacherName",teacherQueryParam.getTeacherName());
        map.put("departmentId",teacherQueryParam.getDepartmentId());
        Date now = new Date();
        map.put("createTime",now);
        map.put("lastModifiedTime",now);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        Integer id = keyHolder.getKey().intValue();
        return id;
    }

    @Override
    public void updateTeacher(Integer teacherId, TeacherRequest teacherRequest) {
        String sql = "UPDATE teacher SET teacher_name = :teacherName, department_id = :departmentId WHERE teacher_id = :teacherId";
        Map<String,Object> map = new HashMap<>();
        map.put("teacherName",teacherRequest.getTeacherName());
        map.put("departmentId",teacherRequest.getDepartmentId());
        map.put("teacherId",teacherId);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteTeacher(Integer teacherId) {
        String sql = "DELETE FROM teacher WHERE teacher_id = :teacherId";
        Map<String,Object> map = new HashMap<>();
        map.put("teacherId",teacherId);
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public Integer getTeachersTotal(TeacherQueryParam teacherQueryParam) {
        String sql = "SELECT COUNT(*) FROM teacher WHERE 1=1";
        Map<String,Object> map = new HashMap<>();
        if(teacherQueryParam.getSearch()!=null){
            sql = sql + " AND teacher_name LIKE :search";
            map.put("search","%"+teacherQueryParam.getSearch()+"%");
        }
        sql = sql +" ORDER BY "+teacherQueryParam.getOrdeyby()+" "+teacherQueryParam.getSort();
        sql = sql +" LIMIT :limit OFFSET :offset";
        map.put("limit",teacherQueryParam.getLimit());
        map.put("offset",teacherQueryParam.getOffset());

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }
}
