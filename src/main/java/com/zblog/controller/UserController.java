package com.zblog.controller;


import com.zblog.common.session.SessionProvider;
import com.zblog.model.Article;
import com.zblog.model.Discuss;
import com.zblog.model.User;
import com.zblog.service.ArticleService;
import com.zblog.service.DiscussService;
import com.zblog.service.MessageService;
import com.zblog.service.UserService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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
    private SessionProvider session;
    @Autowired
    private UserService userService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private DiscussService discussService;
    @Autowired
    private MessageService messageService;
    @RequestMapping("/index.do")
    public ModelAndView reg(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User nowuser  = UserUtil.getUser(request);
        ModelAndView mav=new ModelAndView("/user/index");
        User user = UserUtil.getUser(request);
        List<Article> articleList=articleService.SelectNewArticleByUid(nowuser.getUserid());
        List<Article> articlehot=articleService.SelectHotArticleByUid(nowuser.getUserid());
        List<Discuss> discusshot=discussService.SelectHotDiscussByUid(nowuser.getUserid());
        model.addAttribute("articleList",articleList);
        model.addAttribute("articlehot",articlehot);
        model.addAttribute("discusshot",discusshot);
        return mav;
    }

    @RequestMapping("/family.do")
    public String family(HttpServletRequest request, HttpServletResponse response, ModelMap model) {
        User nowuser  = UserUtil.getUser(request);
        if(nowuser.getUserid()==1){
            return "user/family";
        }else {
            return "redirect:/welcome/index.do";
        }
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
