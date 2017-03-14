package com.zblog.common.adminCheck.Impl;

import com.zblog.common.adminCheck.AdminCheck;
import com.zblog.common.exception.InvalidPasswordException;
import com.zblog.common.exception.UserNotFoundException;
import com.zblog.common.session.HttpSessionProvider;
import com.zblog.common.session.SessionProvider;
import com.zblog.model.User;
import com.zblog.model.UserInfo;
import com.zblog.model.UserRoleKey;
import com.zblog.service.UserInfoService;
import com.zblog.service.UserRoleService;
import com.zblog.service.UserService;
import com.zblog.util.Constants;
import com.zblog.util.UserUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

/**
 * Created by hadoop01 on 16-11-22.
 */
@Service
public class AdminCheckImpl implements AdminCheck{

    private static final Log log = LogFactory.getLog(AdminCheckImpl.class);

    @Autowired
    UserService userService;
    @Autowired
    UserRoleService userRoleService;


    public User adminLogin(String username, String userpwd, HttpServletRequest request, HttpServletResponse response, HttpSessionProvider session) throws UserNotFoundException, InvalidPasswordException {
        User user =this.userService.getUserPWDByUsername(username);

        if (null == user) {
            throw new UserNotFoundException();
        }
        if (!userpwd.equals(user.getUserpwd())) {
            throw new InvalidPasswordException();
        }
        String issuper="1";
        if (user.getUserid()==0)
            issuper="0";
        session.setAttribute(request, response, Constants.SUPER_KEY, issuper);
        session.setAttribute(request, response, UserUtil.USER_KEY, user);

        return user;
    }

    public String retrieveIsSuperFromSession(HttpSessionProvider session, HttpServletRequest request) {
        String isSuper = (String) session.getAttribute(request, Constants.SUPER_KEY);
        if (isSuper == null) {
            return null;
        }
        return isSuper;
    }





    public Set<String> retrievePermsFromSession(HttpSessionProvider session, HttpServletRequest request) {
        Set<String> perms = (Set<String>) session.getAttribute(request, Constants.PERMS_KEY);
        if (perms == null) {
            return null;
        }
        return perms;
    }

    /*
     * 获取管理员session

     */

    public User retrieveUserFromSession(HttpSessionProvider session, HttpServletRequest request) {
        User user = (User) session.getAttribute(request, Constants.AUTH_KEY);
        if (user == null) {
            return null;
        }
        return user;
    }



    private void splitPerms(Set<String> set, String perms) {
        if (perms != null) {
            for (String p : StringUtils.split(perms, ",")) {
                if (!StringUtils.isEmpty(p)) {
                    set.add(p);
                }
            }
        }
    }


    public void storeAuthIdToSession(HttpSessionProvider session, HttpServletRequest request, HttpServletResponse response, String authId) {
        session.setAttribute(request, response, Constants.AUTH_KEY, authId);
    }

}
