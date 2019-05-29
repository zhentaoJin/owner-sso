package com.jzt.sso.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


@ApiModel(value = "日志模型")
@TableName("system_log")
public class SystemLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(value = "id")
	private Long id;
	/**
	 * 手机号
	 */
	@ApiModelProperty(value = "操作人手机号")
	@TableField("op_phone")
	private String opPhone;
	/**
	 * 操作人名字
	 */
	@ApiModelProperty(value = "操作人名字")
	@TableField("op_name")
	private String opName;
	/**
	 * 操作人id
	 */
	@ApiModelProperty(value = "操作人id")
	@TableField("op_id")
	private Long opId;
	/**
	 * 记录时间
	 */
	@ApiModelProperty(value = "记录时间")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@TableField("record_date")
	private Date recordDate;
	/**
	 * 所属机构
	 */
	@ApiModelProperty(value = "所属机构")
	@TableField("op_org")
	private String opOrg;
	/**
	 * 请求ip
	 */
	@ApiModelProperty(value = "请求ip")
	private String ip;
	/**
	 * 请求macip
	 */
	@ApiModelProperty(value = "请求的MacIP")
	@TableField("mac_ip")
	private String macIp;
	/**
	 * 执行时间
	 */
	@ApiModelProperty(value = "执行时间")
	@TableField("op_time")
	private String opTime;
	/**
	 * 执行结果
	 */
	@ApiModelProperty(value = "执行结果")
	@TableField("op_result")
	private String opResult;
	/**
	 * 操作模块
	 */
	@ApiModelProperty(value = "操作模块")
	@TableField("op_entity")
	private String opEntity;
	/**
	 * 执行动作
	 */
	@ApiModelProperty(value = "执行动作")
	@TableField("op_action")
	private String opAction;
	/**
	 * 操作时间
	 */
	@ApiModelProperty(value = "操作时间")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
	@TableField("create_time")
	private Date createTime;

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
	 * 设置：手机号
	 */
	public void setOpPhone(String opPhone) {
		this.opPhone = opPhone;
	}
	/**
	 * 获取：手机号
	 */
	public String getOpPhone() {
		return opPhone;
	}
	/**
	 * 设置：操作人名字
	 */
	public void setOpName(String opName) {
		this.opName = opName;
	}
	/**
	 * 获取：操作人名字
	 */
	public String getOpName() {
		return opName;
	}
	/**
	 * 设置：操作人id
	 */
	public void setOpId(Long opId) {
		this.opId = opId;
	}
	/**
	 * 获取：操作人id
	 */
	public Long getOpId() {
		return opId;
	}
	/**
	 * 设置：记录时间
	 */
	public void setRecordDate(Date recordDate) {
		this.recordDate = recordDate;
	}
	/**
	 * 获取：记录时间
	 */
	public Date getRecordDate() {
		return recordDate;
	}
	/**
	 * 设置：所属机构
	 */
	public void setOpOrg(String opOrg) {
		this.opOrg = opOrg;
	}
	/**
	 * 获取：所属机构
	 */
	public String getOpOrg() {
		return opOrg;
	}
	/**
	 * 设置：请求ip
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：请求ip
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：请求macip
	 */
	public void setMacIp(String macIp) {
		this.macIp = macIp;
	}
	/**
	 * 获取：请求macip
	 */
	public String getMacIp() {
		return macIp;
	}
	/**
	 * 设置：执行时间
	 */
	public void setOpTime(String opTime) {
		this.opTime = opTime;
	}
	/**
	 * 获取：执行时间
	 */
	public String getOpTime() {
		return opTime;
	}
	/**
	 * 设置：执行结果
	 */
	public void setOpResult(String opResult) {
		this.opResult = opResult;
	}
	/**
	 * 获取：执行结果
	 */
	public String getOpResult() {
		return opResult;
	}
	/**
	 * 设置：操作模块
	 */
	public void setOpEntity(String opEntity) {
		this.opEntity = opEntity;
	}
	/**
	 * 获取：操作模块
	 */
	public String getOpEntity() {
		return opEntity;
	}
	/**
	 * 设置：执行动作
	 */
	public void setOpAction(String opAction) {
		this.opAction = opAction;
	}
	/**
	 * 获取：执行动作
	 */
	public String getOpAction() {
		return opAction;
	}
	/**
	 * 设置：操作时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取：操作时间
	 */
	public Date getCreateTime() {
		return createTime;
	}
}
