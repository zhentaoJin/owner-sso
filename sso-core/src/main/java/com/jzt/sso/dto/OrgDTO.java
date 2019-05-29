package com.jzt.sso.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "机构查询模型")
public class OrgDTO {

    @ApiModelProperty(value = "页数",required = true)
    private Integer page;
    @ApiModelProperty(value = "页数量",required = true)
    private Integer limit;
    @ApiModelProperty(value = "状态 0为禁用 1为启用",required = false)
    private Integer status;
    @ApiModelProperty(value = "机构名字",required = false)
    private String orgName;

    private Long userId;

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

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
