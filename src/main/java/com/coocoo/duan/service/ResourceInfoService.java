package com.coocoo.duan.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coocoo.duan.model.ResourceInfo;
import com.coocoo.orm.mapper.ResourceInfoMapper;
import com.coocoo.utils.interceptor.pager.Pager;

@Service
public class ResourceInfoService {
	@Autowired
	private ResourceInfoMapper<ResourceInfo> resourceInfoMapper;
	
	public List<ResourceInfo> getList(){
		return resourceInfoMapper.getList(null);
	}
	
	public List<ResourceInfo> getResourceRole(){
		return resourceInfoMapper.getResourceRole();
	}
	public List<ResourceInfo> getResourceRole(Pager<ResourceInfo> pager){
		return resourceInfoMapper.getResourceRoles(pager);
	}
}
