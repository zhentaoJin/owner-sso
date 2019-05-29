package com.jzt.sso.dto;

import io.swagger.annotations.ApiModelProperty;

public class MenuDTO {

    @ApiModelProperty(value = "页数",required = true)
    private Integer page;
    @ApiModelProperty(value = "页数量",required = true)
    private Integer limit;

    @ApiModelProperty(value = "资源名字",required = false)
    private String name;

    @ApiModelProperty(value = "资源code",required = false)
    private String code;

    @ApiModelProperty(value = "资源类型",required = false)
    private Integer type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
