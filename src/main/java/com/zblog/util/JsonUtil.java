package com.zblog.util;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.InputStreamReader;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class JsonUtil {
	private static Log log = LogFactory.getLog(JsonUtil.class);

	/**
	 * resquest对象转换为jsonStr
	 * @param request
	 * @return
	 */
	public static String getJsonStrFromRequest(HttpServletRequest request) throws Exception {
		DataInputStream in = null;
		request.setCharacterEncoding("utf-8");
		String name = (String)request.getAttribute("name");
		System.setProperty("sun.net.client.defaultConnectTimeout", String.valueOf(10000));
		BufferedReader br = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
		String inputLine = null;
		StringBuffer sb = new StringBuffer();
		while ((inputLine = br.readLine()) != null) {
			sb.append(inputLine).append("\n");
		}
		IOUtils.closeQuietly(in);
		log.info("jsonStr=" + sb.toString());
		return sb.toString();
	}
}
