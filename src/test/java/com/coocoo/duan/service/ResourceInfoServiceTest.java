package com.coocoo.duan.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.coocoo.duan.model.ResourceInfo;
import com.coocoo.duan.model.RoleInfo;
import com.coocoo.utils.interceptor.pager.Pager;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring/service-context.xml")
public class ResourceInfoServiceTest {

	@Autowired
	private ResourceInfoService resourceInfoService;
	@Test
	public void testGetList() {
		List<ResourceInfo>  list = resourceInfoService.getList();
		for (ResourceInfo resourceInfo : list) {
			System.out.println(resourceInfo.getResourceName());
		}
	}
	
	@Test
	public void testGetResourceRole(){
		Pager<ResourceInfo> pager = Pager.DEFAULT;
//		pager.setPageNo(1);
//		pager.setPageSize(5);

		List<ResourceInfo>  list = resourceInfoService.getResourceRole(pager);
		for (ResourceInfo resourceInfo : list) {
			List<RoleInfo> roleInfos = resourceInfo.getRoleInfos();
			String resourceName = resourceInfo.getResourceName() ;
			for (RoleInfo roleInfo:roleInfos) {
				System.out.println(resourceName+ roleInfo.getRoleName());
			}
		}
	}

}
