package com.jzt.sso.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Date;
import java.util.List;


@TableName("user")
@ApiModel(value = "用户模型")
public class UserEntity implements Serializable, GrantedAuthority {

	private static final long serialVersionUID = 1L;

	/**
	 * 自增id
	 */
	@TableId
	@ApiModelProperty(value = "id")
	private Long id;
	/**
	 * 用户名
	 */
	@TableField("user_name")
	@ApiModelProperty(value = "用户名")
	private String userName;
	/**
	 * 密码(存储密文)
	 */
	@ApiModelProperty(value = "密码")
	@JsonIgnore
	private String password;
	/**
	 * 邮箱
	 */
	@ApiModelProperty(value = "邮箱")
	private String email;
	/**
	 * 手机号码
	 */
	@ApiModelProperty(value = "手机号码")
	private String phone;
	/**
	 * 昵称
	 */
	@TableField("nick_name")
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
	@ApiModelProperty(value = "角色代码")
	@TableField("role_code")
	private String roleName;
	/**
	 * 
	 */
	@ApiModelProperty(value = "角色id")
	@TableField("role_id")
	private Long roleId;
	/**
	 * 状态（是否启用）
	 */
	@ApiModelProperty(value = "状态")
	private Long status;
	/**
	 * 机构代码
	 */
	@ApiModelProperty(value = "机构代码")
	@TableField("org_code")
	private Long orgId;
	/**
	 * 机构名字
	 */
	@ApiModelProperty(value = "机构名字")
	@TableField("org_name")
	private String orgName;
	/**
	 * 创建人
	 */
	@JsonIgnore
	@TableField("create_by")
	private Long createBy;
	/**
	 * 创建时间
	 */
	@JsonIgnore
	@TableField("create_time")
	private Date createTime;
	/**
	 * 更新人
	 */
	@JsonIgnore
	@TableField("update_by")
	private Long updateBy;
	/**
	 * 更新时间
	 */
	@JsonIgnore
	@TableField("update_time")
	private Date updateTime;
	/**
	 * 子系统列表
	 */
	@TableField(exist = false)
	private List<MenuEntity> menuEntityList;
	/**
	 * 是否为管理员
	 */
	@TableField("is_admin")
	private Integer isAdmin;

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
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	/**
	 * 获取：机构代码
	 */
	public Long getOrgId() {
		return orgId;
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
	/**
	 * 设置：创建人
	 */
	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}
	/**
	 * 获取：创建人
	 */
	public Long getCreateBy() {
		return createBy;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
	/**
	 * 设置：更新人
	 */
	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
	/**
	 * 获取：更新人
	 */
	public Long getUpdateBy() {
		return updateBy;
	}
	/**
	 * 设置：更新时间
	 */
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	/**
	 * 获取：更新时间
	 */
	public Date getUpdateTime() {
		return updateTime;
	}

	public List<MenuEntity> getMenuEntityList() {
		return menuEntityList;
	}

	public void setMenuEntityList(List<MenuEntity> menuEntityList) {
		this.menuEntityList = menuEntityList;
	}

	public Integer getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Integer isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String getAuthority() {
		return "ROLE_USER";
	}
}
