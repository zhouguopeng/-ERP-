package com.model;

import java.sql.Timestamp;

public class BaseModel {
	/**创建人*/
	private Integer createUser;
	/**创建时间*/
	private Timestamp createTime;
	/**修改人*/
	private Integer editUser;
	/**修改时间*/
	private Integer editTime;
	/**是否启用*/
	private Integer isUsing;
	/**更新回数*/
	private Integer updCount;
	
	private Integer startPage;
	
	private Integer endPage;

	public Integer getCreateUser() {
		return createUser;
	}

	public void setCreateUser(Integer createUser) {
		this.createUser = createUser;
	}

	public Timestamp getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}

	public Integer getEditUser() {
		return editUser;
	}

	public void setEditUser(Integer editUser) {
		this.editUser = editUser;
	}

	public Integer getEditTime() {
		return editTime;
	}

	public void setEditTime(Integer editTime) {
		this.editTime = editTime;
	}

	public Integer getIsUsing() {
		return isUsing;
	}

	public void setIsUsing(Integer isUsing) {
		this.isUsing = isUsing;
	}

	public Integer getUpdCount() {
		return updCount;
	}

	public void setUpdCount(Integer updCount) {
		this.updCount = updCount;
	}

	public Integer getStartPage() {
		return startPage;
	}

	public void setStartPage(Integer startPage) {
		this.startPage = startPage;
	}

	public Integer getEndPage() {
		return endPage;
	}

	public void setEndPage(Integer endPage) {
		this.endPage = endPage;
	}
}
