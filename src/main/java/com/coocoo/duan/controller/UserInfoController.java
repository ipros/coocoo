package com.coocoo.duan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coocoo.duan.model.UserInfo;
import com.coocoo.duan.service.UserInfoService;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController {
		@Autowired
		private UserInfoService userInfoService;
		
		@RequestMapping(value="/list",method=RequestMethod.GET)
		public String list(HttpServletRequest request){
			List<UserInfo> userInfos = userInfoService.findAll();
			request.setAttribute("userInfos", userInfos);
			return "main";
		}
}
