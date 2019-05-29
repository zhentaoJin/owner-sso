package com.jzt.sso.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "状态模型")
public class StatusDTO {

    @ApiModelProperty(value = "用户id",required = false)
    private Integer id;

    @ApiModelProperty(value = "状态 0为禁用 1为启用",required = true)
    private Long status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
