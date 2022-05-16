package com.example.course.dto;

public class TeacherQueryParam {
    private String search;
    private String ordeyby;
    private String sort;
    private Integer limit;
    private Integer offset;

    public String getSearch() {
        return search;
    }

    public void setSearch(String search) {
        this.search = search;
    }

    public String getOrdeyby() {
        return ordeyby;
    }

    public void setOrdeyby(String ordeyby) {
        this.ordeyby = ordeyby;
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
}
