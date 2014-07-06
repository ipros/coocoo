package com.coocoo.duan.service;

import java.util.List;


import com.coocoo.utils.interceptor.pager.Pager;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.coocoo.duan.model.UserInfo;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/service-context.xml")
public class UserInfoServiceTest {
	@Autowired
	private UserInfoService userInfoService;
	
	@Test
	public void test() {
		List<UserInfo> userInfos = userInfoService.findAll();
		for (UserInfo userInfo : userInfos) {
			System.out.println(userInfo.getUsername());
		}
		
	}
	
	@Test
	public void testAddUser() {
		UserInfo userInfo = new UserInfo();
		userInfo.setUsername("eee");
		userInfo.setPassword("eeee");
		Assert.assertEquals(true, userInfoService.addUser(userInfo));
	}
	
	@Test
	public void testGetUserRole() {
		UserInfo userInfo = userInfoService.getUserRoleByUsername("admin");
			System.out.println(userInfo.getUsername());
	}
    @Test
    public void testGetUserInfosByPage(){
        Pager<UserInfo> pager = Pager.DEFAULT;
        List<UserInfo> userInfos = userInfoService.getUserInfosByPage(pager);
        for (UserInfo userInfo : userInfos) {
            System.out.println(userInfo.getUsername());
        }
    }

}
