package com.coocoo.duan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.coocoo.utils.interceptor.pager.Pager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.coocoo.duan.model.UserInfo;
import com.coocoo.duan.service.UserInfoService;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/userInfo")
public class UserInfoController{
	@Autowired
	private UserInfoService userInfoService;
		
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String list(HttpServletRequest request){
	    List<UserInfo> userInfos = userInfoService.findAll();
		request.setAttribute("userInfos", userInfos);
		return "system/userInfoList";
	}
    @RequestMapping(value="listPage",method=RequestMethod.POST)
    public @ResponseBody String listPage(){
        Pager<UserInfo> pager = Pager.DEFAULT;
        List<UserInfo> userInfos = userInfoService.getUserInfosByPage(pager);
        return userInfos.toString();
    }

    /**
     * 超时
     * @return
     */
    @RequestMapping(value="timeOut",method=RequestMethod.GET)
    public String timeOut(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("message", "登录超时，请重新登录");
        return "redirect:/";
    }
    /**
     * 阻止2次登录
     * @return
     */
    @RequestMapping(value="isLogin",method=RequestMethod.GET)
    public String isLogin(HttpServletRequest request){
        HttpSession session = request.getSession();
        session.setAttribute("message", "此账号已在其它地方登录");
        return "redirect:/";
    }

}
