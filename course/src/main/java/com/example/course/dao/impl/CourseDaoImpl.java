package com.example.course.dao.impl;

import com.example.course.dao.CourseDao;
import com.example.course.dto.CourseRequest;
import com.example.course.dto.QueryParam;
import com.example.course.model.Course;
import com.example.course.rowmapper.CourseRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class CourseDaoImpl implements CourseDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Course getCourseById(String courseId) {
        String sql = "SELECT course_id,course_name,point,teacher_name,stu_limit,week,class,description,c.create_time,c.last_modified_time FROM course c INNER JOIN teacher USING (teacher_id) WHERE course_id = :courseId";
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseId);

        List<Course> courseList = namedParameterJdbcTemplate.query(sql,map,new CourseRowMapper());

        if(courseList.size()>0)
            return courseList.get(0);
        else
            return null;
    }
    @Override
    public List<Course> getCourses(QueryParam queryParam) {
        String sql = "SELECT course_id,course_name,point,teacher_name,stu_limit,week,class,description,c.create_time,c.last_modified_time FROM course c INNER JOIN teacher USING (teacher_id) WHERE 1=1";
        Map<String,Object> map = new HashMap<>();

        sql = addSql(sql,map,queryParam);

        sql = sql + " ORDER BY " + queryParam.getOrderby() + " " + queryParam.getSort();
        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit",queryParam.getLimit());
        map.put("offset",queryParam.getOffset());
        List<Course> courseList = namedParameterJdbcTemplate.query(sql,map,new CourseRowMapper());
        return courseList;
    }

    @Override
    public Integer getCoursesPage(QueryParam queryParam) {
        String sql = "SELECT COUNT(*) FROM course INNER JOIN teacher USING (teacher_id) WHERE 1=1";
        Map<String,Object> map = new HashMap<>();

        sql = addSql(sql,map,queryParam);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql,map,Integer.class);
        return total;
    }

    @Override
    public String createCourse(CourseRequest courseRequest) {
        String sql = "INSERT INTO course VALUES(:courseId,:courseName,:point,:teacherId,:stuLimit,:week,:class,:description,:createTime,:lastModifiedTime)";
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseRequest.getCourseId());
        addMap(sql,map,courseRequest);
        Date now = new Date();
        map.put("createTime",now);
        map.put("lastModifiedTime",now);

        namedParameterJdbcTemplate.update(sql,map);
        return courseRequest.getCourseId();
    }

    @Override
    public void updateCourse(String courseId, CourseRequest courseRequest) {
        String sql = "UPDATE course SET course_name = :courseName,point = :point,teacher_id = :teacherId,stu_limit = :stuLimit,week = :week,class = :class,description = :description,last_modified_time = :lastModifiedTime WHERE course_id = :courseId";
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseId);
        addMap(sql,map,courseRequest);
        Date now = new Date();
        map.put("lastModifiedTime",now);
        System.out.println(courseRequest.getCourseName());
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteCourse(String courseId) {
        String sql = "DELETE FROM course WHERE course_id = :courseId";
        Map<String,Object> map = new HashMap<>();
        map.put("courseId",courseId);
        namedParameterJdbcTemplate.update(sql,map);
    }

    private String addSql(String sql , Map<String,Object> map , QueryParam queryParam){
        if(queryParam.getWeek() != null){
            sql = sql + " AND week = :week";
            map.put("week",queryParam.getWeek().name());
        }
        if(queryParam.get_class() != null){
            sql = sql + " AND class LIKE :class";
            map.put("class","%"+queryParam.get_class()+"%");
        }
        if(queryParam.getSearch() != null){
            sql = sql + " AND course_name LIKE :courseName";
            map.put("courseName","%"+queryParam.getSearch()+"%");
        }

        return sql;
    }
    private void addMap(String sql,Map<String,Object> map,CourseRequest courseRequest){
        map.put("courseName",courseRequest.getCourseName());
        map.put("point",courseRequest.getPoint());
        map.put("teacherId",courseRequest.getTeacherId());
        map.put("stuLimit",courseRequest.getStuLimit());
        map.put("week",courseRequest.getWeek().name());
        map.put("class",courseRequest.get_class());
        map.put("description",courseRequest.getDescription());
    }
}
