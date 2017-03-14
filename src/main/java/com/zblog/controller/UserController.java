package com.zblog.controller;


import com.zblog.model.User;
import com.zblog.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService  userService;


    @RequestMapping("/index.do")
    public ModelAndView reg(HttpServletRequest request, HttpServletResponse response) {
        User nowuser  = UserUtil.getUser(request);
        ModelAndView mav=new ModelAndView("/user/index");
     //   mav.addObject("users",users);
        String string="success";
        mav.addObject("nowuser",nowuser);
        return mav;
    }

    @RequestMapping("/list.do")
    public ModelAndView alluser(HttpServletRequest request, HttpServletResponse response) {
        List<User> users=userService.SelectAll();
        System.out.println(users);
        System.out.println("ok");
        User user1 = UserUtil.getUser(request);

        HttpSession s = request.getSession();
        // s.setAttribute("userDeptId", userDeptId);
        // s.setAttribute("dtlv", dtlv);
        System.out.println(user1.getUsername()+"index");
        ModelAndView mav=new ModelAndView("/users");
        return mav;
    }

}
