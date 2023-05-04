package cn.jeefast.common.xss;

import org.apache.commons.lang.StringUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * XSSfilterate
 */
public class XssFilter implements Filter {

	private List<String> excludedList = new ArrayList<String>();

	@Override
	public void init(FilterConfig config) throws ServletException {

		/*
		 */
		excludedList.add("/sysXinwen/update");
		excludedList.add("/sysXinwen/save");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(
				(HttpServletRequest) request);
		String url = xssRequest.getServletPath();
		if(isExcluded(url)){
			chain.doFilter(request, response);
		}else{
			chain.doFilter(xssRequest, response);
		}
	}

	@Override
	public void destroy() {
	}

	/**
	 */
	private boolean isExcluded(String url){
		if(StringUtils.isBlank(url)){
			return false;
		}

		for (String excluded : excludedList) {
			if(url.contains(excluded)){
				return true;
			}
		}
		return false;
	}

}