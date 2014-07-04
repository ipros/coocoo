package com.coocoo.orm.mapper;

import java.util.List;

import com.coocoo.duan.model.UserInfo;
import com.coocoo.orm.mapper.base.BaseSqlMapper;

public interface UserInfoMapper<T extends UserInfo> extends  BaseSqlMapper<T>{
	public List<T> findAll();
	
	public T getUserRoleByUsername(String username);
}
