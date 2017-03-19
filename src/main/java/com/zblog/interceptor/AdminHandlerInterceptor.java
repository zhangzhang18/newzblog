package com.zblog.interceptor;

import java.util.LinkedHashSet;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zblog.common.session.HttpSessionProvider;
import org.springframework.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import org.springframework.web.util.UrlPathHelper;

import com.zblog.common.adminCheck.AdminCheck;
import com.zblog.common.session.SessionProvider;
import com.zblog.model.User;
import com.zblog.util.Constants;
import com.zblog.util.FilesPros;
import com.zblog.util.UserUtil;

public class AdminHandlerInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

		String uri = AdminHandlerInterceptor.getURI(request);
		// 不在验证的范围内
		if (exclude(uri)) {
			return true;
		}
		// 获得用户
		User user = null;
		user = this.adminCheck.retrieveUserFromSession(this.session, request);
		if (user == null) {
			response.sendRedirect(getLoginUrl(request));
			System.err.println("登陆拦截");
			return false;
		}
        System.out.println("preinterceptor");
		// 获取用户权限
		Set<String> perms = new LinkedHashSet<String>();
		perms = this.adminCheck.retrievePermsFromSession(this.session, request);

		// 获取是否为超级管理员
		String isSuper = this.adminCheck.retrieveIsSuperFromSession(this.session, request);

		UserUtil.setUser(request, user);

		UserUtil.setSuper(request, isSuper);

		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mav) throws Exception {
		User user = UserUtil.getUser(request);
		// 不控制权限时perm为null，PermistionDirective标签将以此作为依据不处理权限问题。
		if (user != null && mav != null && mav.getModelMap() != null && mav.getViewName() != null) {

		}
	}

	/**
	 * 获得第三个路径分隔符的位置
	 * 
	 * @param request
	 * @throws IllegalStateException
	 *             访问路径错误，没有三(四)个'/'
	 */
	private static String getURI(HttpServletRequest request) throws IllegalStateException {
		UrlPathHelper helper = new UrlPathHelper();
		String uri = helper.getOriginatingRequestUri(request);
		// System.out.println(request.getScheme() + "://" +
		// request.getServerName() + ":" + request.getServerPort() + uri);
		String ctxPath = helper.getOriginatingContextPath(request);
		int isDomain = Integer.valueOf(FilesPros.getProper("IS_DOMAIN"));
		int devMode = Integer.valueOf(FilesPros.getProper("DEV_MODE"));
		// 是域名访问，则直接返回url
		if (isDomain == 1) {
			return uri;
		}
		// count=1则取两级目录，如/welcome/index.do
		// count=1则取一级目录，如/index.do
		int start = 0, i = 0, count = devMode;
		if (!StringUtils.isEmpty(ctxPath)) {
			count++;
		}
		while (i < count && start != -1) {
			start = uri.indexOf('/', start + 1);
			i++;
		}
		if (start <= 0) {
			throw new IllegalStateException("admin access path not like '/inpics/inpic/...' pattern: " + uri);
		}
		return uri.substring(start);
	}

	private AdminCheck adminCheck;

	private String[] excludeUrls; // 允许的url

	private String returnUrl;

	private HttpSessionProvider session;

	private boolean exclude(String uri) {
		if (this.excludeUrls != null) {
			for (String exc : this.excludeUrls) {
				if (exc.equals(uri)) {
					return true;
				}
			}
		}
		return false;
	}

	public AdminCheck getadminCheck() {
		return this.adminCheck;
	}

	public String[] getExcludeUrls() {
		return this.excludeUrls;
	}

	private String getLoginUrl(HttpServletRequest request) {
		StringBuilder buff = new StringBuilder();
		String ctx = request.getContextPath();
		if (!StringUtils.isEmpty(ctx)) {
			buff.append(ctx);
		}
		return buff.append("/welcome/login.do").toString();

	}

	public String getReturnUrl() {
		return this.returnUrl;
	}

	public SessionProvider getSession() {
		return this.session;
	}
	
	@Autowired
	public void setadminCheck(AdminCheck adminCheck) {
		this.adminCheck = adminCheck;
	}

	public void setExcludeUrls(String[] excludeUrls) {
		this.excludeUrls = excludeUrls;
	}

	public void setReturnUrl(String returnUrl) {
		this.returnUrl = returnUrl;
	}

	@Autowired
	public void setSession(HttpSessionProvider session) {
		this.session = session;
	}

}
