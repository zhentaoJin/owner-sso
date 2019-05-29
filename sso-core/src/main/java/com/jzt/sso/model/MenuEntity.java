package com.jzt.sso.model;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

/**
 * 
 * 
 * @author JinZhentao
 * @email 1660645816@qq.com
 * @date 2018-12-27 17:19:22
 */
@TableName("menu")
@ApiModel(value = "菜单模型")
public class MenuEntity implements  Serializable, GrantedAuthority {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	@ApiModelProperty(value = "id")
	private Long id;
	/**
	 * 菜单名字
	 */
	@ApiModelProperty(value = "菜单名字")
	private String name;
	/**
	 * 菜单编码
	 */
	@ApiModelProperty(value = "菜单编码")
	private String code;
	/**
	 * 菜单类型  0为模块菜单 1为业务系统
	 */
	@ApiModelProperty(value = "菜单类型  0为模块菜单 1为业务系统")
	private Integer type;
	/**
	 * 菜单描述
	 */
	@ApiModelProperty(value = "菜单描述")
	private String desc;
	/**
	 * 菜单状态 0为禁用 1为启用
	 */
	@ApiModelProperty(value = "菜单状态 0为禁用 1为启用")
	private Integer status;
	/**
	 * 菜单url
	 */
	@ApiModelProperty(value = "菜单url ")
	private String url;
	/**
	 * 系统iconUrl
	 */
	@TableField("icon_url")
	@ApiModelProperty(value = "系统iconUrl ")
	private String iconUrl;
	/**
	 * 父级菜单id
	 */
	@ApiModelProperty(value = "父级菜单id ")
	private Long pid;

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
	 * 设置：菜单名字
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * 获取：菜单名字
	 */
	public String getName() {
		return name;
	}
	/**
	 * 设置：菜单编码
	 */
	public void setCode(String code) {
		this.code = code;
	}
	/**
	 * 获取：菜单编码
	 */
	public String getCode() {
		return code;
	}
	/**
	 * 设置：菜单类型
	 */
	public void setType(Integer type) {
		this.type = type;
	}
	/**
	 * 获取：菜单类型
	 */
	public Integer getType() {
		return type;
	}
	/**
	 * 设置：菜单描述
	 */
	public void setDesc(String desc) {
		this.desc = desc;
	}
	/**
	 * 获取：菜单描述
	 */
	public String getDesc() {
		return desc;
	}
	/**
	 * 设置：状态
	 */
	public void setStatus(Integer status) {
		this.status = status;
	}
	/**
	 * 获取：状态
	 */
	public Integer getStatus() {
		return status;
	}
	/**
	 * 设置：菜单url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取：菜单url
	 */
	public String getUrl() {
		return url;
	}
	/**
	 * 设置：系统iconUrl
	 */
	public void setIconUrl(String iconUrl) {
		this.iconUrl = iconUrl;
	}
	/**
	 * 获取：系统iconUrl
	 */
	public String getIconUrl() {
		return iconUrl;
	}

	public Long getPid() {
		return pid;
	}

	public void setPid(Long pid) {
		this.pid = pid;
	}

	@Override
	public String getAuthority() {
		return this.url;
	}
}
