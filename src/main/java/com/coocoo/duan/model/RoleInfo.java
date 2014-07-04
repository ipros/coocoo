package com.coocoo.duan.model;

import java.util.List;

/**
 * 角色
 * @author adam
 *
 */
public class RoleInfo {
	private int id;  //主键
	
	private String roleName;  //角色名称

	public int getId() {
		return id;
	}
	
	public List<ResourceInfo> resourceInfos;
	
	public List<UserInfo> userInfos;

	public void setId(int id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public List<ResourceInfo> getResourceInfos() {
		return resourceInfos;
	}

	public void setResourceInfos(List<ResourceInfo> resourceInfos) {
		this.resourceInfos = resourceInfos;
	}

	public List<UserInfo> getUserInfos() {
		return userInfos;
	}

	public void setUserInfos(List<UserInfo> userInfos) {
		this.userInfos = userInfos;
	}
	
	
	
	
}
