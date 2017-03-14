package com.zblog.util;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author ray.wang
 * @date 2013-3-6 上午10:47:12
 * @DESC HttpServletResponse帮助类
 */
public final class ResponseUtils {

	private static final Log log = LogFactory.getLog(ResponseUtils.class);

	/**
	 * @DESC 发送内容，使用UTF-8编码
	 * 
	 * @param response
	 * @param contentType
	 * @param text
	 */
	public static void render(HttpServletResponse response, String contentType, String text) {
		response.setContentType(contentType);
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		try {
			response.getWriter().write(text);
		}
		catch (IOException e) {
			ResponseUtils.log.error(e.getMessage(), e);
		}
	}

	/**
	 * @DESC 发送json，使用UTF-8编码
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderJson(HttpServletResponse response, String text) {
		ResponseUtils.render(response, "application/json;charset=UTF-8", text);
	}

	/**
	 * @DESC 发送文本使用UTF-8编码
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送字符串
	 */
	public static void renderText(HttpServletResponse response, String text) {
		ResponseUtils.render(response, "text/plain;charset=UTF-8", text);
	}

	/**
	 * @DESC 发送xml。使用UTF-8编码
	 * 
	 * @param response
	 *            HttpServletResponse
	 * @param text
	 *            发送的字符串
	 */
	public static void renderXml(HttpServletResponse response, String text) {
		ResponseUtils.render(response, "text/xml;charset=UTF-8", text);
	}
}
