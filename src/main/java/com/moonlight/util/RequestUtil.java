package com.moonlight.util;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 请求/响应处理工具类
 * 
 * @author sunan
 * @since 2020-04-01
 */
public class RequestUtil {

	static String logTagEq = "==========";
	static String logTagPl = "*************************";

	private static final Logger logger = LoggerFactory.getLogger(RequestUtil.class);

	/**
	 * 打印请求信息
	 * 
	 * @param request
	 */
	public static void printRequest(HttpServletRequest request) {
		String requestURI = request.getRequestURI();
		if("/error".equals(requestURI)) {
			return;
		}
		logger.info(getLogWithTag(request.getRequestURI(), logTagPl));
		logger.info(getLogWithTag("headers begin", logTagEq));
		Enumeration<String> headerNames = request.getHeaderNames();
		while (headerNames.hasMoreElements()) {
			String headerName = headerNames.nextElement();
			logger.info("{}:{}", headerName, request.getHeader(headerName));
		}
		logger.info(getLogWithTag("headers end", logTagEq));
		logger.info(getLogWithTag("params begin", logTagEq));
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			String parameterName = parameterNames.nextElement();
			logger.info("{}:{}", parameterName, request.getParameter(parameterName));
		}
		logger.info(getLogWithTag("params end", logTagEq));
		logger.info(getLogWithTag(request.getRequestURI().concat(" finish"), logTagPl));
	}

	/**
	 * 获取指定符号格式的日志文本
	 * 例如"==========headers begin=========="
	 * 
	 * @param logText 文本
	 * @param logTag 符号
	 * @return logWithTag
	 */
	public static String getLogWithTag(String logText, String logTag) {
		return new StringBuffer().append(logTag).append(logText).append(logTag).toString();
	}
}
