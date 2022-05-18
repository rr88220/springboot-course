package com.example.course.dao.impl;

import com.example.course.dao.TeacherDao;
import com.example.course.dto.QueryParam;
import com.example.course.dto.TeacherRequest;
import com.example.course.model.Teacher;
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
        String sql = "SELECT teacher_id,teacher_name,department_name,create_time,last_modified_time FROM teacher INNER JOIN department USING(department_id) WHERE teacher_id = :teacherId";
        Map<String,Object> map = new HashMap<>();
        map.put("teacherId",teacherId);

        List<Teacher> teacherList = namedParameterJdbcTemplate.query(sql, map, new TeacherRowMapper());
        if(teacherList.size()>0)
            return teacherList.get(0);
        else
            return null;
    }

    @Override
    public List<Teacher> getTeachers(QueryParam queryParam) {
        String sql = "SELECT teacher_id,teacher_name,department_name,create_time,last_modified_time FROM teacher INNER JOIN department USING(department_id) WHERE 1=1";
        Map<String,Object> map = new HashMap<>();

        sql = addSql(sql,map,queryParam);

        sql = sql +" ORDER BY "+ queryParam.getOrderby()+" "+ queryParam.getSort();
        sql = sql +" LIMIT :limit OFFSET :offset";
        map.put("limit", queryParam.getLimit());
        map.put("offset", queryParam.getOffset());

        List<Teacher> teacherList = namedParameterJdbcTemplate.query(sql, map, new TeacherRowMapper());
        return teacherList;
    }

    @Override
    public Integer getTeachersTotal(QueryParam queryParam) {
        String sql = "SELECT COUNT(*) FROM teacher WHERE 1=1";
        Map<String,Object> map = new HashMap<>();

        sql = addSql(sql,map,queryParam);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }
    @Override
    public Integer createTeacher(TeacherRequest queryParam) {
        String sql = "INSERT INTO teacher(teacher_name,department_id,create_time,last_modified_time) VALUES (:teacherName,:departmentId,:createTime,:lastModifiedTime)";
        Map<String,Object> map = new HashMap<>();
        map.put("teacherName",queryParam.getTeacherName());
        map.put("departmentId",queryParam.getDepartmentId());
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
    private String addSql(String sql , Map<String,Object> map , QueryParam queryParam){
        if(queryParam.getDepartment()!=null){
            sql = sql + " AND department_name = :departmentName";
            map.put("departmentName",queryParam.getDepartment());
        }
        if(queryParam.getSearch()!=null){
            sql = sql + " AND teacher_name LIKE :search";
            map.put("search","%"+ queryParam.getSearch()+"%");
        }

        return sql;
    }

}
