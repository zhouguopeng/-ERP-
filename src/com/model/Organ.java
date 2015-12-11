package com.model;

public class Organ {
	/**
	 * 单位id
	 */
	private long organId;
	/**
	 * 单位名称
	 */
	private String organName;
	/**
	 * 上级单位id
	 */
	private long parentId;
	
	public long getOrganId() {
		return organId;
	}
	public void setOrganId(long organId) {
		this.organId = organId;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public long getParentId() {
		return parentId;
	}
	public void setParentId(long parentId) {
		this.parentId = parentId;
	}
	
	

}
