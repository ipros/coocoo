package com.coocoo.utils.security;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.coocoo.duan.model.RoleInfo;
import com.coocoo.duan.model.UserInfo;
import com.coocoo.duan.service.UserInfoService;


public class UserDetailServiceImpl implements UserDetailsService {
	private Logger log = Logger.getLogger(UserDetailServiceImpl.class);
	@Autowired
	private UserInfoService userInfoService;
	
	public UserDetails loadUserByUsername(String userName)
			throws UsernameNotFoundException {
		log.info("Login user --- " + userName);
		UserInfo userInfo = userInfoService.getUserRoleByUsername(userName);
        
        if(userInfo==null){  
            throw new UsernameNotFoundException("用户名不存在!");  
        }
          
        boolean enabled = true;                //是否可用  
        boolean accountNonExpired = true;        //是否过期  
        boolean credentialsNonExpired = true;     
        boolean accountNonLocked = true;    
          
        Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();  
        //如果你使用资源和权限配置在xml文件中，如：<intercept-url pattern="/user/admin" access="hasRole('ROLE_ADMIN')"/>；  
        //并且也不想用数据库存储，所有用户都具有相同的权限的话，你可以手动保存角色(如：预订网站)。  
        //authorities.add(new SimpleGrantedAuthority("ROLE_USER"));  
         
        List<RoleInfo> roleInfos = userInfo.getRoleInfos();
        for(RoleInfo roleInfo : roleInfos){  
        	//保存角色
            GrantedAuthority ga = new SimpleGrantedAuthority(roleInfo.getRoleName());  
            authorities.add(ga);      
        }  
        return new User(  
                userInfo.getUsername(),  
                userInfo.getPassword(),   
                enabled,   
                accountNonExpired,   
                credentialsNonExpired,   
                accountNonLocked,   
                authorities); 
    }

}
