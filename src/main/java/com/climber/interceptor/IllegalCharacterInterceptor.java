package com.climber.interceptor;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class IllegalCharacterInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String name = names.nextElement();
			String[] values = request.getParameterValues(name);
			for (String value : values) {
				// sql注入直接拦截
				if (judgeSQLInject(value.toLowerCase())) {
					response.setContentType("text/html;charset=UTF-8");
					response.getWriter().print("参数含有非法攻击字符,已禁止继续访问！");
					return false;
				}
				//防止xss攻击
				clearXss(value);
//				XssUtil.stripXss(value);
			}
		}

		return true;
	}

	public boolean judgeSQLInject(String value) {
		if (value == null || "".equals(value)) {
			return false;
		}
		String xssStr = "and|or|select|update|delete|drop|truncate|%20|=|-|--|;|'|%|#|+|//|/| |\\|!=|(|)";
		String[] xssArr = xssStr.split("\\|");
		for (int i = 0; i < xssArr.length; i++) {
			if (value.indexOf(xssArr[i]) > -1) {
				return true;
			}
		}
		return false;
	}

	private String clearXss(String value) {
		if (value == null || "".equals(value)) {
			return value;
		}
		value = value.replaceAll("<", "<").replaceAll(">", ">");
		value = value.replaceAll("\\(", "(").replace("\\)", ")");
		value = value.replaceAll("'", "'");
		value = value.replaceAll("eval\\((.*)\\)", "");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
		value = value.replace("script", "");
		return value;
	}

}
