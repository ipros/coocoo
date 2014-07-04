package com.coocoo.utils.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.coocoo.common.config.Config;
import com.coocoo.duan.model.UserInfo;
import com.coocoo.duan.service.UserInfoService;


/**
 * @description spring security 认证成功后的处理
 * @author duanleilei
 * @date 2014-03-20
 */
public class LoginAuthenticationSuccesssHandler implements
		AuthenticationSuccessHandler {
	
	@Autowired
	private UserInfoService userInfoService;
	//默认跳转页面
	private String defaultUrl;

	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
	
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) throws IOException,
			ServletException {
		HttpSession session = request.getSession();
		UserDetails userDetails =  getSession();
		UserInfo userInfo = userInfoService.getUserRoleByUsername(userDetails.getUsername());
		//保存登录用户到session
		session.setAttribute(Config.getProperty("SESSION_USER").toString(),userInfo );
		session.removeAttribute("message");
		//登录成功跳转页面
		response.sendRedirect(request.getContextPath() + defaultUrl);
	}
	
	//获取security登录用户
		private  UserDetails getSession(){
			SecurityContext ctx = SecurityContextHolder.getContext();
			Authentication auth = ctx.getAuthentication();
			
			Object obj = auth.getPrincipal();
			
			if(obj instanceof UserDetails){
				return (UserDetails)obj;
			}else{
				return null;
			}
		}
}
