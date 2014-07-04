package com.coocoo.utils.security;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;


/** 
 * @description 一个自定义的filter， 
 *  必须包含authenticationManager,accessDecisionManager,securityMetadataSource三个属性， 
        我们的所有控制将在这三个类中实现 
 * @author adam 
 * @date 2013-10-25
 */  
public class FilterSecurityInterceptor extends AbstractSecurityInterceptor implements Filter{
	private FilterInvocationSecurityMetadataSource securityMetadataSource;  
    
    /* (non-Javadoc) 
     * @see org.springframework.security.access.intercept.AbstractSecurityInterceptor#getSecureObjectClass() 
     */  
    @Override  
    public Class<?> getSecureObjectClass() {  
        return FilterInvocation.class;  
    }  
  
    @Override  
    public SecurityMetadataSource obtainSecurityMetadataSource() {  
        return securityMetadataSource;  
    }  
  
 
    public void destroy() {}  
      

    public void doFilter(ServletRequest request, ServletResponse response,  
            FilterChain chain) throws IOException, ServletException {  
        //super.beforeInvocation(fi);源码    
        //1.获取请求资源的权限    
            //执行Collection<ConfigAttribute> attributes = SecurityMetadataSource.getAttributes(object);    
        //2.是否拥有权限    
            //this.accessDecisionManager.decide(authenticated, object, attributes);    
        FilterInvocation fi = new FilterInvocation(request, response, chain);  
        InterceptorStatusToken token = super.beforeInvocation(fi);  
        try {  
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());  
        } catch (Exception e) {  
            e.printStackTrace();  
        }finally{  
            super.afterInvocation(token,null);  
        }  
    }  
   

    public void init(FilterConfig config) throws ServletException {  
          
    }

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}  
      
      
  
	
}
