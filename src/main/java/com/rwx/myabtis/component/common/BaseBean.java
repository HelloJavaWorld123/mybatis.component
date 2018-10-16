package com.rwx.myabtis.component.common;

import java.util.Date;
import java.util.Objects;

/**
 * Author：RXK
 * Date:2018/10/15 22:19
 * Version: V1.0.0
 * Des: 生成DaoBean时的基类
 * 将公共的部分抽取出来，再生成model时继承该基类
 **/
public class BaseBean {
	private Integer id;
	private Date createTime;
	private String createBy;
	private String updateBy;
	private Date updateTime;
	private Integer deleteFlag;

	public BaseBean() {
	}

	public BaseBean(int id, Date createTime, String createBy, String updateBy, Date updateTime, Integer deleteFlag) {
		this.id = id;
		this.createTime = createTime;
		this.createBy = createBy;
		this.updateBy = updateBy;
		this.updateTime = updateTime;
		this.deleteFlag = deleteFlag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Integer getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(Integer deleteFlag) {
		this.deleteFlag = deleteFlag;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		BaseBean baseBean = (BaseBean) o;
		return Objects.equals(id, baseBean.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
