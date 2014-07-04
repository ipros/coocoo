package com.coocoo.utils.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.AntPathMatcher;

import com.coocoo.duan.model.ResourceInfo;
import com.coocoo.duan.model.RoleInfo;
import com.coocoo.duan.service.ResourceInfoService;


/**
 * 加载资源与权限的对应关系 
 * @author adam
 *
 */

public class SecurityMetadataSource implements FilterInvocationSecurityMetadataSource{
	private Logger log = Logger.getLogger(SecurityMetadataSource.class);

	private ResourceInfoService resourceInfoService;
	
	
    /* 保存资源和权限的对应关系  key-资源url  value-权限 */  
    private Map<String,Collection<ConfigAttribute>> resourceMap = null;   
    private AntPathMatcher urlMatcher = new AntPathMatcher(); 

    public SecurityMetadataSource(ResourceInfoService resourceInfoService){
    	this.resourceInfoService = resourceInfoService;
    	loadResourcesDefine();
    }
    
    /**
     * 加载角色资源
     * 格式 资源地址(url)-->[角色1,角色2]
     * 如 http://localhost/survey/*-->[ROLE_ADMIN,ROLE_MANAGER]
     */
    private void loadResourcesDefine(){  
        resourceMap = new HashMap<String,Collection<ConfigAttribute>>();  
//		  根据角色关联资源        
//        List<RoleInfo> roleInfos = roleInfoService.getRoleInfos(); 
//        for(RoleInfo roleInfo : roleInfos){  
//            List<ResourceInfo> resources = resourceInfoService.getResourcesByRname(roleInfo.getRoleName());  
//            for(ResourceInfo resourceInfo : resources){  
//                Collection<ConfigAttribute> configAttributes = null;  
//                ConfigAttribute configAttribute = new SecurityConfig(roleInfo.getRoleName());  
//                if(resourceMap.containsKey(resourceInfo.getUrl())){  
//                    configAttributes = resourceMap.get(resourceInfo.getUrl());  
//                    configAttributes.add(configAttribute);  
//                }else{  
//                    configAttributes = new ArrayList<ConfigAttribute>() ;  
//                    configAttributes.add(configAttribute);  
//                    resourceMap.put(resourceInfo.getUrl(), configAttributes);  
//                }  
//            }  
//        }  
        
        //根据资源关联角色
        List<ResourceInfo> resources = resourceInfoService.getResourceRole();
        for(ResourceInfo resourceInfo : resources){
        	List<RoleInfo> roleInfos = resourceInfo.getRoleInfos();
        	Collection<ConfigAttribute> configAttributes = new ArrayList<ConfigAttribute>() ;  
            ConfigAttribute configAttribute = null; 
        	if(CollectionUtils.isNotEmpty(roleInfos)){
        		for (RoleInfo roleInfo : roleInfos) {
        			configAttribute = new SecurityConfig(roleInfo.getRoleName());
        			configAttributes.add(configAttribute);
				}
        	}else{
        		configAttribute = new SecurityConfig("UNKNOW_ROLE_DEFIND");//如果资源没有对应的角色,则默认UNKNOW
    			configAttributes.add(configAttribute);
        	}
        	if(resourceMap.containsKey(resourceInfo.getResourceUrl())){  
                configAttributes = resourceMap.get(resourceInfo.getResourceUrl());  
                configAttributes.add(configAttribute);  
            }else{  
                resourceMap.put(resourceInfo.getResourceUrl(), configAttributes);  
            }
        }
    }  
    
    
    
    
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		
        return null;  
	}

	
	/*****
	 *	通过访问资源(_url)返回角色集合 
	 *
     * url为请求地址
     * _url为资源地址
     * 如果请求地址带有"?",那么资源地址只用保存"?"之前的url
     * 如：请求地址 http://localhost/survey?id=1  则资源地址写成  http://localhost/survey 即可与之匹配
     * 如果请求url为rest风格,则资源资源地址的url之后的用“*”表示
     * 如：1.请求地址 http://localhost/survey/1/test  则资源地址写成  http://localhost/survey/* 即可与之匹配
     * 	   2.请求地址 http://localhost/survey/1/test?pwd=123456  则资源地址写成  http://localhost/survey/* 即可与之匹配
     */
	public Collection<ConfigAttribute> getAttributes(Object obj)
			throws IllegalArgumentException {
		//获取请求的url地址  
        String url = ((FilterInvocation)obj).getRequestUrl();  
        log.debug("Request url ---- " + url);
        Iterator<String> it = resourceMap.keySet().iterator();  
        while(it.hasNext()){  
            String _url = it.next();  
//            if(_url.indexOf("?")!=-1){  
//                _url = _url.substring(0, _url.indexOf("?"));  
//            }
            if(url.indexOf("?")!=-1){   
            	url = url.substring(0, url.indexOf("?"));  //带参数的url
            }
            if(urlMatcher.match(_url,url))  
                return resourceMap.get(_url);  
        }  
		return null;
	}

	public boolean supports(Class<?> arg0) {
		// TODO Auto-generated method stub
		return true;
	}



	public void setUrlMatcher(AntPathMatcher urlMatcher) {
		this.urlMatcher = urlMatcher;
	}




}
