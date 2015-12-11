package com.model;

import java.sql.Timestamp;

public class User {
	
	/**
	 * 员工id
	 */
	private Long id;
	/**
	 *用户ID
	 */
	private String userId ;
	/**
	 *员工登录名
	 */
	private String userName;
	/**
	 *员工密码
	 */
	private String password;
	/**
	 *邮箱地址
	 */
	private String email ;
	/**
	 * 所属角色
	 */
	private Integer roleId ;
	/**
	 * 仓库id
	 */
	private Integer whId;
	/**
	 * 备注
	 */
	private String notes;
	/**
	 * 状态
	 */
	private String status;
	/**创建时间*/
	private Timestamp createTime;
	/**创建人*/
	private String createUser;
	/**修改人*/
	private String updateUser;
	/**修改时间*/
	private Timestamp updateTime;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getRoleId() {
		return roleId;
	}
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	public Integer getWhId() {
		return whId;
	}
	public void setWhId(Integer whId) {
		this.whId = whId;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Timestamp getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Timestamp createTime) {
		this.createTime = createTime;
	}
	public String getCreateUser() {
		return createUser;
	}
	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}
	public String getUpdateUser() {
		return updateUser;
	}
	public void setUpdateUser(String updateUser) {
		this.updateUser = updateUser;
	}
	public Timestamp getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Timestamp updateTime) {
		this.updateTime = updateTime;
	}

}
