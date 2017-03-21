package com.zblog.common.adminCheck;

import com.zblog.common.exception.InvalidPasswordException;
import com.zblog.common.exception.UserNotFoundException;
import com.zblog.common.session.HttpSessionProvider;
import com.zblog.model.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Created by hadoop01 on 16-11-22.
 */
public interface AdminCheck {
    public User adminLogin(String username, String userpwd, HttpServletRequest request, HttpServletResponse response, HttpSessionProvider session)
            throws UserNotFoundException, InvalidPasswordException;
    /**
     * @DESC 后台管理从session中获取是否为超级管理员
     * @param session
     * @param request
     * @return
     */
    public String retrieveIsSuperFromSession(HttpSessionProvider session, HttpServletRequest request);

    /**
     * @DESC 后台管理从session中获取用户权限
     * @param session
     * @param request
     * @return
     */
    public Set<String> retrievePermsFromSession(HttpSessionProvider session, HttpServletRequest request);

    /**
     * @DESC 后台管理从session中获取用户
     * @param session
     * @param request
     * @return
     */
    public User retrieveUserFromSession(HttpSessionProvider session, HttpServletRequest request);


    /**
     * @DESC 存储用户认证ID到session
     * @param session
     * @param request
     * @param response
     * @param authId
     */
    public void storeAuthIdToSession(HttpSessionProvider session, HttpServletRequest request, HttpServletResponse response, String authId);

}