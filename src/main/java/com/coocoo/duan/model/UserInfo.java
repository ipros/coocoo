package com.coocoo.duan.model;

import java.util.List;

/**
 * 用户
 * @author adam
 *
 */
public class UserInfo {
	private Integer id;
	
	private String username;
	
	private String password;

	private List<RoleInfo> roleInfos;
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<RoleInfo> getRoleInfos() {
		return roleInfos;
	}

	public void setRoleInfos(List<RoleInfo> roleInfos) {
		this.roleInfos = roleInfos;
	}
	
	
}
