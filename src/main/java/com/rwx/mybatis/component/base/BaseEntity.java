package com.rwx.mybatis.component.base;

import java.util.Date;

/**
 * @author : RXK
 * @version : v1.0.0
 * @Date : 2019/7/28 15:03
 * @Des: 基类
 */
public abstract class BaseEntity {

	private Long id;

	private Date createTime;

	private Date updateTime;

	private Long createBy;

	private Long updateBy;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Long getCreateBy() {
		return createBy;
	}

	public void setCreateBy(Long createBy) {
		this.createBy = createBy;
	}

	public Long getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(Long updateBy) {
		this.updateBy = updateBy;
	}
}
