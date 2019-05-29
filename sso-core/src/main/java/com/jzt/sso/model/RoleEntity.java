package com.jzt.sso.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@TableName("role")
@ApiModel(value = "角色模型")
public class RoleEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(value = "id")
	private Long id;
	/**
	 * 角色名字
	 */
	@ApiModelProperty(value = "名字")
	private String name;
	/**
	 * 角色描述
	 */
	@ApiModelProperty(value = "描述")
	private String desc;
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
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
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
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	private Date updateTime;

	@TableField("org_id")
	@ApiModelProperty(value = "机构id")
	private Long orgId;

	@ApiModelProperty(value = "机构名字")
	@TableField("org_name")
	private String orgName;

	@TableField(exist = false)
	@ApiModelProperty(value = "权限")
	private List<MenuEntity> menuEntityList;

	@TableField(exist = false)
	@ApiModelProperty(value = "权限id集合")
	private List<Long>  menuIds;
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
	 * 设置：角色名字
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：角色名字
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：角色描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：角色描述
	 */
	public String getDesc() {
		return desc;
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

	public List<Long> getMenuIds() {
		return menuIds;
	}

	public void setMenuIds(List<Long> menuIds) {
		this.menuIds = menuIds;
	}


	public Long getOrgId() {
		return orgId;
	}

	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
}
