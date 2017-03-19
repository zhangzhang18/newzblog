package com.zblog.controller;

import com.zblog.common.adminCheck.AdminCheck;
import com.zblog.common.exception.InvalidPasswordException;
import com.zblog.common.exception.UserNotFoundException;
import com.zblog.common.session.HttpSessionProvider;
import com.zblog.common.session.SessionProvider;
import com.zblog.model.User;
import com.zblog.util.Constants;
import com.zblog.util.RequestUtils;
import com.zblog.util.UserUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by hadoop01 on 16-11-21.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    private static final Log log = LogFactory.getLog(LoginController.class);

    @Autowired
    private AdminCheck adminCheck;

    @Autowired
    private SessionProvider session;

    @Autowired
    private HttpSessionProvider sessionProvider;

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String input(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        String returnUrl = RequestUtils.getQueryParam(request, Constants.RETURN_URL);
        User user = (User) this.sessionProvider.getAttribute(request, UserUtil.USER_KEY);
        // 存在认证信息
        if (user != null) {
            System.out.println(user.getUsername()+"get");
            System.out.println("redirect:../welcome/index.do");
            return "redirect:/user/index.do";
        }
        if (!StringUtils.isEmpty(returnUrl)) {
            model.addAttribute(Constants.RETURN_URL, returnUrl);
        }
        return "redirect:/welcome/login.do";
    }

    @RequestMapping(value = "/logout.do")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        User user = (User) this.sessionProvider.getAttribute(request, UserUtil.USER_KEY);
        if (null != user) {
            this.sessionProvider.logout(request, response);
        }
        return "redirect:/welcome/index.do";
    }

    @RequestMapping(value = "/login.do", method = RequestMethod.POST)
    public String submit(String username, String userpwd, String captcha, HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User user = new User();
//        String errors = validateSubmit(username, password, captcha, request, response);
         String errors = "";
        if (StringUtils.isEmpty(errors)) {
            try {
                user = this.adminCheck.adminLogin(username, userpwd, request, response, this.sessionProvider);
                if (null == user) {
                    model.addAttribute("errors", "用户不存在!");
                    System.out.println("用户不存在");
                    return "welcome/login";
                }
            }
            catch (UserNotFoundException e) {
                model.addAttribute("errors", "用户不存在!");
                System.out.println("用户不存在");
                return "welcome/login";
            }
            catch (InvalidPasswordException e) {
                model.addAttribute("errors", "用户密码错误!");
                System.out.println("用户密码错误");
                return "welcome/login";
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            if (user != null) {
                System.out.println(user.getUsername()+"post");
                model.addAttribute("userName", user.getUsername());
                model.addAttribute("user", user);
            }
        } else {
            // 设置登录失败信息

        //    model.addAttribute("user", user);
            model.addAttribute("errors", errors);
            return "welcome/login";
        }

        // 判断是否是当日值班员

        return "redirect:/user/index.do";
    }
/*    private String validateSubmit(String username, String password, String captcha, HttpServletRequest request, HttpServletResponse response) {
        if (StringUtils.isEmpty(captcha)) {
            return "验证码不能为空";
        }

        try {
            if (!this.imageCaptchaService.validateResponseForID(this.session.getSessionId(request, response), captcha)) {
                return "验证码错误";
            }
        }
        catch (CaptchaServiceException e) {
            LoginController.log.warn("", e);
            return "验证码异常";
        }
        return "";
    }*/

}