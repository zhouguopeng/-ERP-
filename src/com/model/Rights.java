package com.model;

import java.sql.Timestamp;
/**
 * 权限
 * @author mayongfeng
 *
 */
public class Rights {
	/**权限id*/
	private Integer rightId;
	/**父id*/
	private Integer parentId;
	/**等级*/
	private String level;
	/**权限类别*/
	private String model;
	/**名称*/
	private String rightName;
	/**图标*/
	private String icon;
	/**访问路径*/
	private String url;
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
	
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getRightId() {
		return rightId;
	}
	public void setRightId(Integer rightId) {
		this.rightId = rightId;
	}
	public Integer getParentId() {
		return parentId;
	}
	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	public String getRightName() {
		return rightName;
	}
	public void setRightName(String rightName) {
		this.rightName = rightName;
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
}
