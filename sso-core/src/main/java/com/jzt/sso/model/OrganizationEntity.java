package com.jzt.sso.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;

@TableName("organization")
@ApiModel(value = "机构模型")
public class OrganizationEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(value = "id")
	private Long id;
	/**
	 * 机构名字
	 */
	@ApiModelProperty(value = "机构名字")
	@TableField("org_name")
	private String orgName;
	/**
	 * 联系人名字
	 */
	@ApiModelProperty(value = "联系人名字")
	@TableField("concat_name")
	private String concatName;
	/**
	 * 联系人电话
	 */
	@ApiModelProperty(value = "联系人电话")
	@TableField("concat_phone")
	private String concatPhone;
	/**
	 * 联系人地址
	 */
	@ApiModelProperty(value = "联系人地址")
	@TableField("concat_address")
//	@NotBlank(message = "联系人地址不能为空。")
	private String concatAddress;
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

	@ApiModelProperty(value = "状态 0为禁用 1为启用")
	private Integer status;

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
	 * 设置：联系人名字
	 */
	public void setConcatName(String concatName) {
		this.concatName = concatName;
	}
	/**
	 * 获取：联系人名字
	 */
	public String getConcatName() {
		return concatName;
	}
	/**
	 * 设置：联系人电话
	 */
	public void setConcatPhone(String concatPhone) {
		this.concatPhone = concatPhone;
	}
	/**
	 * 获取：联系人电话
	 */
	public String getConcatPhone() {
		return concatPhone;
	}
	/**
	 * 设置：联系人地址
	 */
	public void setConcatAddress(String concatAddress) {
		this.concatAddress = concatAddress;
	}
	/**
	 * 获取：联系人地址
	 */
	public String getConcatAddress() {
		return concatAddress;
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
