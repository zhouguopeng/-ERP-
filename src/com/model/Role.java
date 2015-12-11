package com.model;

public class Role {
	/**
	 * 角色id
	 */
	private long roleId;
	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 权限
	 * 【add,edit,delete,select,export】,多权限用逗号隔开
	 */
	private String rights;
	public long getRoleId() {
		return roleId;
	}
	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRights() {
		return rights;
	}
	public void setRights(String rights) {
		this.rights = rights;
	}
	

}
