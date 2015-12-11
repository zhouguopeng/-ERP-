package com.model;

import java.sql.Timestamp;

public class User {
	/**
	 *  员工id
	 */
	private long userId ;
	/**
	 *  员工登录名
	 */
	private String userName;
	/**
	 *  员工密码
	 */
	private String password;
	/**
	 * 所属部门
	 */
	private long organId ;
	/**
	 * 所属角色
	 */
	private long roleId ;
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
	/**用户类型*/
	private String userType;
	
	/**--------------------------------扩展属性-------------------------------*/
	/**
	 * 所属部门信息
	 */
	private Organ organ;
	/**
	 * 所属部门名称
	 */
	private String organName;
	/**
	 * 所属角色信息
	 */
	private Role role;
	/**
	 * 所属角色名称
	 */
	private String roleName;
	
	
	
	public long getOrganId() {
		return organId;
	}
	public void setOrganId(long organId) {
		this.organId = organId;
	}
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
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
	public Organ getOrgan() {
		return organ;
	}
	public void setOrgan(Organ organ) {
		this.organ = organ;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	
	public Role getRole() {
		return role;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
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
	
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	

}
