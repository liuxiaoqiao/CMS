package com.foxconn.security.interceptor;

import java.beans.PropertyEditorSupport;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.web.context.ServletContextAware;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.foxconn.security.SecurityManager;

public class MethodFilterInvocation implements ServletContextAware,InitializingBean {

	private ServletContext servlet;
	
	public void setServletContext(ServletContext servlet){
		this.servlet=servlet;
	}
	
	@SuppressWarnings({ "unused", "rawtypes" })
	public ConfigAttribute getAttributes(Method method, Class clazz) {
        Map<String, String> methodAuthorities = this.getMethodAuthorities();
        String grantedAuthorities = null;
        for(Iterator<Map.Entry<String, String>> iter = methodAuthorities.entrySet().iterator(); iter.hasNext();) {
            Map.Entry<String, String> entry = iter.next();
            String methodStr = entry.getKey();            
            grantedAuthorities = entry.getValue();            
        }
        
        if(grantedAuthorities != null) {
        	PropertyEditorSupport configAttrEditor = new PropertyEditorSupport();
            configAttrEditor.setAsText(grantedAuthorities);
            return (ConfigAttribute) configAttrEditor.getValue();
        }
        return null;
	}

	public ConfigAttribute getAttributes(Object arg0)
			throws IllegalArgumentException {
		System.out.println("getAttributes");
		return null;
	}

	@SuppressWarnings("rawtypes")
	public Collection getConfigAttributeDefinitions() {
		System.out.println("getconfigattributeDefinitions");
		return null;
	}

	@SuppressWarnings("rawtypes")
	public boolean supports(Class arg0) {
		return true;
	}

	public void afterPropertiesSet() throws Exception {
		System.out.println("afterPropertiesSet");
	}
	
	@SuppressWarnings("unchecked")
	private Map<String, String> getMethodAuthorities() {
		if(servlet.getAttribute("methodAuthorities")!=null){
			 return (Map<String, String>)servlet.getAttribute("methodAuthorities");
		}
		else {
		  SecurityManager securityManager=(SecurityManager) WebApplicationContextUtils.getWebApplicationContext(servlet).getBean("securityManager");
		   Map<String, String> methods= (Map<String, String>)securityManager.loadMethodAuthorities();
		   servlet.setAttribute("methodAuthorities", methods);
		   
		   return methods;
		}		     
       
    }
}
