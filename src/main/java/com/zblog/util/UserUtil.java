package com.zblog.util;

import javax.servlet.http.HttpServletRequest;

import com.zblog.model.User;

public class UserUtil {

	/**
	 * 用户权限key
	 */
	public static final String PERMS_KEY = "_perms_key";

	/**
	 * 是否超级管理员
	 */
	public static final String SUPER_KEY = "_super_key";

	/**
	 * 用户KEY
	 */
	public static final String USER_KEY = "user";

	public static final String MOBILE_KEY = "_mobile_key";

	/**
	 * 获得是否为超级管理员
	 * 
	 * @param request
	 * @return
	 */
	public static String getSuper(HttpServletRequest request) {
		return (String) request.getAttribute(UserUtil.SUPER_KEY);
	}

	/**
	 * 获得用户
	 * 
	 * @param request
	 * @return
	 */
	public static User getUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(UserUtil.USER_KEY);
	}


	/**
	 * 设置超级管理员标识
	 * 
	 * @param request
	 * @param
	 */
	public static void setSuper(HttpServletRequest request, String isSuper) {
		request.setAttribute(UserUtil.SUPER_KEY, isSuper);
	}

	/**
	 * 设置用户
	 * 
	 * @param request
	 * @param user
	 */
	public static void setUser(HttpServletRequest request, User user) {
		request.setAttribute(UserUtil.USER_KEY, user);
	}

}
