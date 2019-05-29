package com.jzt.sso.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "通用查询模型")
public class CommonDTO {

    @ApiModelProperty(value = "页数",required = true)
    private Integer page;
    @ApiModelProperty(value = "页数量",required = true)
    private Integer limit;

    @ApiModelProperty(value = "机构id",required = false)
    private Long orgId;

    @ApiModelProperty(value = "状态",required = false)
    private Long status;

    @ApiModelProperty(value = "用户姓名",required = false)
    private String username;

    @ApiModelProperty(value = "用户账号",required = false)
    private String phone;

    @ApiModelProperty(value = "角色id",required = false)
    private Long roleId;

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
