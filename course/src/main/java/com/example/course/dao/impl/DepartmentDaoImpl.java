package com.example.course.dao.impl;

import com.example.course.dao.DepartmentDao;
import com.example.course.dto.DepartmentQueryParam;
import com.example.course.dto.DepartmentRequest;
import com.example.course.model.Department;
import com.example.course.rowmapper.DepartmentRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class DepartmentDaoImpl implements DepartmentDao {
    @Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Department getDepartmentById(Integer departmentId) {
        String sql = "SELECT department_id,department_name FROM department WHERE department_id = :departmentId";
        Map<String,Object> map = new HashMap<>();
        map.put("departmentId",departmentId);

        List<Department> departmentList = namedParameterJdbcTemplate.query(sql,map,new DepartmentRowMapper());

        if(departmentList.size()>0)
            return departmentList.get(0);
        else
            return null;
    }

    @Override
    public List<Department> getDepartments(DepartmentQueryParam departmentQueryParam) {
        String sql = "SELECT department_id,department_name FROM department WHERE 1=1";
        Map<String,Object> map = new HashMap<>();

        if(departmentQueryParam.getSearch()!=null){
            sql = sql + " AND department_name LIKE :search";
            map.put("search","%"+departmentQueryParam.getSearch()+"%");
        }

        sql = sql + " ORDER BY "+departmentQueryParam.getOrderby()+" "+departmentQueryParam.getSort();

        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit",departmentQueryParam.getLimit());
        map.put("offset",departmentQueryParam.getOffset());
        List<Department> departmentList = namedParameterJdbcTemplate.query(sql,map,new DepartmentRowMapper());
        return departmentList;
    }

    @Override
    public Integer createDepartment(DepartmentRequest departmentRequest) {
        String sql = "INSERT INTO department(department_name) VALUES (:departmentName)";
        Map<String,Object> map = new HashMap<>();
        map.put("departmentName",departmentRequest.getDepartmentName());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        namedParameterJdbcTemplate.update(sql,new MapSqlParameterSource(map),keyHolder);
        Integer id = keyHolder.getKey().intValue();
        return id;
    }

    @Override
    public void updateDepartment(Integer departmentId,DepartmentRequest departmentRequest) {
        String sql = "UPDATE department SET department_name = :departmentName WHERE department_id = :departmentId";
        Map<String,Object> map = new HashMap<>();
        map.put("departmentId",departmentId);
        map.put("departmentName",departmentRequest.getDepartmentName());
        namedParameterJdbcTemplate.update(sql,map);
    }

    @Override
    public void deleteDepartment(Integer departmentId) {
        String sql = "DELETE FROM department WHERE department_id = :departmentId";
        Map<String,Object> map = new HashMap<>();
        map.put("departmentId",departmentId);
        namedParameterJdbcTemplate.update(sql,map);
    }
}