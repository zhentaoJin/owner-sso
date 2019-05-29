package com.jzt.sso.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "密码模型")
public class PasswordDTO {

    @ApiModelProperty(value = "用户id",required = false)
    private Integer id;

    @ApiModelProperty(value = "旧密码",required = true)
    private String oldPassword;

    @ApiModelProperty(value = "新密码",required = true)
    private String newPassword;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }
}
