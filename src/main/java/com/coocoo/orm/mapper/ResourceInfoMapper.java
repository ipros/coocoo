package com.coocoo.orm.mapper;

import java.util.List;

import com.coocoo.duan.model.ResourceInfo;
import com.coocoo.orm.mapper.base.BaseSqlMapper;
import com.coocoo.utils.interceptor.pager.Pager;

public interface ResourceInfoMapper<T extends ResourceInfo> extends BaseSqlMapper<T>{
	public List<T> getResourceRole();
	
	public List<T> getResourceRoles(Pager<T> pager);
}
