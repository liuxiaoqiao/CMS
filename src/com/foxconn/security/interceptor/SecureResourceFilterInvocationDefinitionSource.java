/**
 * 
 */
package com.foxconn.security.interceptor;

import java.beans.PropertyEditorSupport;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.AntUrlPathMatcher;
import org.springframework.security.web.util.RegexUrlPathMatcher;
import org.springframework.security.web.util.UrlMatcher;

public class SecureResourceFilterInvocationDefinitionSource implements
		FilterInvocationSecurityMetadataSource, InitializingBean {

	private UrlMatcher urlMatcher;

	private boolean useAntPath = true;

	private boolean lowercaseComparisons = true;

	/**
	 * @param useAntPath
	 *            the useAntPath to set
	 */
	public void setUseAntPath(boolean useAntPath) {
		this.useAntPath = useAntPath;
	}

	/**
	 * @param lowercaseComparisons
	 */
	public void setLowercaseComparisons(boolean lowercaseComparisons) {
		this.lowercaseComparisons = lowercaseComparisons;
	}

	public void afterPropertiesSet() throws Exception {

		this.urlMatcher = new RegexUrlPathMatcher();

		if (useAntPath) { // change the implementation if required
			this.urlMatcher = new AntUrlPathMatcher();
		}

		if ("true".equals(lowercaseComparisons)) {
			if (!this.useAntPath) {
				((RegexUrlPathMatcher) this.urlMatcher)
						.setRequiresLowerCaseUrl(true);
			}
		} else if ("false".equals(lowercaseComparisons)) {
			if (this.useAntPath) {
				((AntUrlPathMatcher) this.urlMatcher)
						.setRequiresLowerCaseUrl(false);
			}
		}

	}


	@SuppressWarnings("rawtypes")
	public Collection getConfigAttributeDefinitions() {
		return null;
	}


	/**
	 * 
	 * @param filterInvocation
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Map<String, String> getUrlAuthorities(
			FilterInvocation filterInvocation) {
		ServletContext servletContext = filterInvocation.getHttpRequest()
				.getSession().getServletContext();
		return (Map<String, String>) servletContext
				.getAttribute("urlAuthorities");
	}

	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object)
			throws IllegalArgumentException {
		FilterInvocation filterInvocation = (FilterInvocation) object;
		String requestURI = filterInvocation.getRequestUrl();
		requestURI = requestURI.replace("/", "");
		Map<String, String> urlAuthorities = this
				.getUrlAuthorities(filterInvocation);
		String grantedAuthorities = null;
		for (Iterator<Map.Entry<String, String>> iter = urlAuthorities
				.entrySet().iterator(); iter.hasNext();) {
			Map.Entry<String, String> entry = iter.next();
			String url = entry.getKey();
			if (urlMatcher.pathMatchesUrl(url, requestURI)) {
				grantedAuthorities = entry.getValue();
				break;
			}
		}

		if (grantedAuthorities != null) {
			PropertyEditorSupport configAttrEditor = new PropertyEditorSupport();
			configAttrEditor.setAsText(grantedAuthorities);
			return (Collection<ConfigAttribute>) configAttrEditor.getValue();
		}
		return null;
	}

}
