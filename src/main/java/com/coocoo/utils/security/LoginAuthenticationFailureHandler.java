package com.coocoo.utils.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 * @description spring security 认证失败后的处理
 * @author duanleilei
 * @date 2014-03-20
 */ 
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {

	//默认跳转页面
	private String defaultUrl;
	
	public void setDefaultUrl(String defaultUrl) {
		this.defaultUrl = defaultUrl;
	}
	public void onAuthenticationFailure(HttpServletRequest request,
			HttpServletResponse response, AuthenticationException authenticationException)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		String message = "";
		if(("User is disabled").equals(authenticationException.getMessage())){
			message = "用户名无效或已删除";
		}else if(("Bad credentials").equals(authenticationException.getMessage())){
			message = "用户名或密码错误";
		}
		session.setAttribute("message", message);
		response.sendRedirect(request.getContextPath() + defaultUrl);
	}

	

}
