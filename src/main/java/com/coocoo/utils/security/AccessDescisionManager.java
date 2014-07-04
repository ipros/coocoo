package com.coocoo.utils.security;

import java.util.Collection;
import java.util.Iterator;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

/** 
 * @description  访问决策器，决定某个用户具有的角色，是否有足够的权限去访问某个资源 ;做最终的访问控制决定 
 * @author adam 
 * @date 2013-10-25 
 */ 
public class AccessDescisionManager implements AccessDecisionManager{

	public void decide(Authentication authentication, Object arg1,
			Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
        if(configAttributes==null) 
        	return;  
        Iterator<ConfigAttribute> it = configAttributes.iterator();  
        while(it.hasNext()){  
            String needRole  = it.next().getAttribute();;
            //authentication.getAuthorities()  用户所有的权限  
            for(GrantedAuthority ga:authentication.getAuthorities()){  
                if(needRole.equals(ga.getAuthority())){  
                    return;  
                }  
            }  
        }  
        throw new AccessDeniedException("AccessDescisionManager：decide()-------AccessDenied faild！");  
		
	}

	public boolean supports(ConfigAttribute arg0) {
		// TODO Auto-generated method stub
		return true;
	}

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}

}
