package com.jzt.sso.dto;

import com.baomidou.mybatisplus.annotations.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

@ApiModel(value = "用户模型")
public class UserDTO  implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     *
     */
    @TableId
    @ApiModelProperty(value = "id")
    private Long id;
    /**
     * 用户名
     */
    @ApiModelProperty(value = "用户名")
    private String userName;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
//    @NotBlank(message = "密码不能为空。")
    private String password;
    /**
     * 邮箱
     */
    @ApiModelProperty(value = "邮箱")
//    @NotBlank(message = "邮箱不能为空。")
    private String email;
    /**
     * 手机号码
     */
    @ApiModelProperty(value = "手机号码")
    private String phone;
    /**
     * 昵称
     */
    @ApiModelProperty(value = "昵称")
    private String nickName;
    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    private String desc;
    /**
     * 角色代码
     */
    @ApiModelProperty(value = "角色名字")
    private String roleName;
    /**
     *
     */
    @ApiModelProperty(value = "角色id")
    private Long roleId;
    /**
     * 状态（是否启用 0为禁用 1为启用）
     */
    @ApiModelProperty(value = "状态")
    private Long status;
    /**
     * 机构代码
     */
    @ApiModelProperty(value = "机构代码")
    private Long orgCode;
    /**
     * 机构名字
     */
    @ApiModelProperty(value = "机构名字")
    private String orgName;

    @ApiModelProperty(value = "业务系统ids")
    private Long[] menuList;

    @ApiModelProperty(value = "角色ids")
    private Long[] roleIds;

    /**
     * 设置：
     */
    public void setId(Long id) {
        this.id = id;
    }
    /**
     * 获取：
     */
    public Long getId() {
        return id;
    }
    /**
     * 设置：用户名
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }
    /**
     * 获取：用户名
     */
    public String getUserName() {
        return userName;
    }
    /**
     * 设置：密码
     */
    public void setPassword(String password) {
        this.password = password;
    }
    /**
     * 获取：密码
     */
    public String getPassword() {
        return password;
    }
    /**
     * 设置：邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }
    /**
     * 获取：邮箱
     */
    public String getEmail() {
        return email;
    }
    /**
     * 设置：手机号码
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    /**
     * 获取：手机号码
     */
    public String getPhone() {
        return phone;
    }
    /**
     * 设置：昵称
     */
    public void setNickName(String nickName) {
        this.nickName = nickName;
    }
    /**
     * 获取：昵称
     */
    public String getNickName() {
        return nickName;
    }
    /**
     * 设置：描述
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }
    /**
     * 获取：描述
     */
    public String getDesc() {
        return desc;
    }
    /**
     * 设置：角色代码
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * 获取：角色代码
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * 设置：
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
    /**
     * 获取：
     */
    public Long getRoleId() {
        return roleId;
    }
    /**
     * 设置：状态（是否启用）
     */
    public void setStatus(Long status) {
        this.status = status;
    }
    /**
     * 获取：状态（是否启用）
     */
    public Long getStatus() {
        return status;
    }
    /**
     * 设置：机构代码
     */
    public void setOrgCode(Long orgCode) {
        this.orgCode = orgCode;
    }
    /**
     * 获取：机构代码
     */
    public Long getOrgCode() {
        return orgCode;
    }
    /**
     * 设置：机构名字
     */
    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
    /**
     * 获取：机构名字
     */
    public String getOrgName() {
        return orgName;
    }

    public Long[] getMenuList() {
        return menuList;
    }

    public void setMenuList(Long[] menuList) {
        this.menuList = menuList;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }
}

