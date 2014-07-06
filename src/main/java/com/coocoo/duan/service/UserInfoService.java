package com.coocoo.duan.service;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coocoo.duan.model.UserInfo;
import com.coocoo.orm.mapper.UserInfoMapper;

@Service
public class UserInfoService {

	@Autowired
	private UserInfoMapper< UserInfo> userInfoMapper;

	public List<UserInfo> findAll() {
//		System.out.println(mapper.findAll().size());
		return userInfoMapper.getList(null);
	}

    public List<UserInfo> getUserInfosByPage(Pager<UserInfo> pager){
        return userInfoMapper.getListByPager(pager);
    }

	public boolean addUser(UserInfo userInfo){
		return userInfoMapper.add(userInfo);
	}
	
	public UserInfo getUserRoleByUsername(String username){
		return userInfoMapper.getUserRoleByUsername(username);
	}
}
