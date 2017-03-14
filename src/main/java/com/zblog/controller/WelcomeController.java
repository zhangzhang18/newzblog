package com.zblog.controller;

import com.zblog.common.session.SessionProvider;
import com.zblog.model.Article;
import com.zblog.model.Discuss;
import com.zblog.model.Message;
import com.zblog.model.User;
import com.zblog.service.ArticleService;
import com.zblog.service.DiscussService;
import com.zblog.service.MessageService;
import com.zblog.service.UserService;
import com.zblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.util.List;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

/**
 * Created by hadoop01 on 16-11-21.
 */
@Controller
@RequestMapping("/welcome")
public class WelcomeController {
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
    public String index(HttpServletRequest request, ModelMap model) {
         User user = UserUtil.getUser(request);
        List<Article> articleList=articleService.SelectNewArticleByZcm();
        List<Article> articlehot=articleService.SelectHotArticleByZcm();
        List<Discuss> discusshot=discussService.SelectHotDiscussByZcm();
        model.addAttribute("articleList",articleList);
        model.addAttribute("articlehot",articlehot);
        model.addAttribute("discusshot",discusshot);
        return "/welcome/index";
    }
    @RequestMapping("/hello.do")
    public String hello() {
     return "welcome/hello";
    }
    @RequestMapping(value = "/register.do",method = RequestMethod.GET)
    public String register() {
        return "welcome/register";
    }
    @RequestMapping(value = "/register.do",method = RequestMethod.POST)
    public String registerPOST(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String userpwd = request.getParameter("userpwd");
        User newuser = new User();
        newuser.setEmail(email);
        newuser.setUsername(username);
        newuser.setUserpwd(userpwd);
        int insert = this.userService.insert(newuser);
        if (insert!=0) {
            System.out.println("用户数据插入成功！！！");
        } else {
            System.out.println("用户数据插入失败！！！");
        }
        return "welcome/login";
    }
    @RequestMapping("/login.do")
    public String login() {
        return "welcome/login";
    }
    @RequestMapping("/article.do")
    public ModelAndView article() {
        List<Article> articleList=articleService.SelectArticleByZcm();
        ModelAndView mav=new ModelAndView("/welcome/article");
        mav.addObject("articleList",articleList);
        return mav;
    }
    @RequestMapping(value = "/articledetail.do")
    public ModelAndView articledetail(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("articleid"));
        String id=request.getParameter("articleid");
        Article article=articleService.selectByPrimaryKey(Integer.parseInt(id));
        articleService.addWcount(article);
        ModelAndView mav=new ModelAndView("/welcome/articledetail");
        mav.addObject("article",article);
        return mav;
    }
    @RequestMapping("/message.do")
    public ModelAndView message() {
        ModelAndView mav = new ModelAndView("/welcome/message");
        List<Message> messageList = messageService.SelectAllMessage();
        mav.addObject("messageList", messageList);
        return mav;
    }
    @RequestMapping("/discuss.do")
    public ModelAndView discuss() {
        ModelAndView mav = new ModelAndView("/welcome/discuss");
        List<Discuss> discussList = discussService.SelectAllDiscuss();
        mav.addObject("discussList", discussList);
        return mav;
    }

    @RequestMapping("/discussdetail.do")
    public ModelAndView discuss(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/welcome/discussdetail");
        System.out.println(request.getParameter("discussid"));
        String id=request.getParameter("discussid");
        Discuss discuss=discussService.selectByPrimaryKey(Integer.parseInt(id));
        discussService.addWcount(discuss);
        List<Discuss> discussreply=discussService.SelectReplyById(Integer.parseInt(id));
        mav.addObject("discuss", discuss);
        mav.addObject("discussreply", discussreply);
        return mav;
    }
    @RequestMapping("/aboutme.do")
    public String aboutme() {
        return "welcome/aboutme";
    }
}
