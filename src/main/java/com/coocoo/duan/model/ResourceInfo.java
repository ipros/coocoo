package com.coocoo.duan.model;

import java.util.List;

/**
 * 系统链接资源
 * @author adam
 *
 */
public class ResourceInfo {
	private Integer id;  //主键
	
	private String resourceName; //资源名称
	
	private String resourceUrl;  //资源链接
	
	private List<RoleInfo> roleInfos;  //对应角色

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getResourceName() {
		return resourceName;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public String getResourceUrl() {
		return resourceUrl;
	}

	public void setResourceUrl(String resourceUrl) {
		this.resourceUrl = resourceUrl;
	}

	public List<RoleInfo> getRoleInfos() {
		return roleInfos;
	}

	public void setRoleInfos(List<RoleInfo> roleInfos) {
		this.roleInfos = roleInfos;
	}
}
