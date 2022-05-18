package com.example.course.dto;

import com.example.course.constant.CourseWeek;
import com.example.course.constant.DepartmentCategory;

public class QueryParam {
    private DepartmentCategory category;
    private String department;
    private String search;
    private String orderby;
    private String sort;
    private Integer limit;
    private Integer offset;
    private CourseWeek week;
    private String _class;

    public DepartmentCategory getCategory() {
        return category;
    }

    public void setCategory(DepartmentCategory category) {
        this.category = category;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrderby() {
        return orderby;
    }

    public void setOrderby(String orderby) {
        this.orderby = orderby;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public CourseWeek getWeek() {
        return week;
    }

    public void setWeek(CourseWeek week) {
        this.week = week;
    }

    public String get_class() {
        return _class;
    }

    public void set_class(String _class) {
        this._class = _class;
    }
}
