package com.zblog.controller;

import com.zblog.common.page.Pagination;
import com.zblog.common.page.SimplePage;
import com.zblog.common.session.SessionProvider;
import com.zblog.model.Article;
import com.zblog.model.Discuss;
import com.zblog.model.User;
import com.zblog.service.ArticleService;
import com.zblog.service.DiscussService;
import com.zblog.service.MessageService;
import com.zblog.service.UserService;
import com.zblog.util.Constants;
import com.zblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
        log.println("访问首页");
        System.err.println("访问首页");
        return "/welcome/index";
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
    public String article(Integer pageNo,ModelMap modelMap,HttpServletRequest request, HttpServletResponse response) {

        String type=request.getParameter("type");
        if (type==null){
            Pagination pagination = this.articleService.getPage(
                    SimplePage.cpn(pageNo), Constants.PAGE_SIZE, 0);
            modelMap.addAttribute("pagination",pagination);
            System.err.println("文章");
        }else{
            Pagination pagination = this.articleService.getPagea(
                    SimplePage.cpn(pageNo), Constants.PAGE_SIZE, 0,Integer.parseInt(type));
            modelMap.addAttribute("pagination",pagination);
            System.err.println("文章");
        }

        return "/welcome/article";
    }
    @RequestMapping(value = "/articledetail.do")
    public ModelAndView articledetail(HttpServletRequest request, HttpServletResponse response) {
        System.out.println(request.getParameter("articleid"));
        String id=request.getParameter("articleid");
        Article article=articleService.selectByPrimaryKey(Integer.parseInt(id));
        articleService.addWcount(article);
        ModelAndView mav=new ModelAndView("article/articledetail");
        mav.addObject("article",article);
        log.println("文章内容");
        System.err.println("文章内容");
        return mav;
    }
    @RequestMapping("/message.do")
    public String message(Integer pageNo,ModelMap modelMap) {
        Pagination pagination = this.messageService.getPage(
                SimplePage.cpn(pageNo), Constants.PAGE_SIZE, 0);
        modelMap.addAttribute("pagination",pagination);
        modelMap.addAttribute("pagination",pagination);
        return "/welcome/message";
    }
    @RequestMapping("/discuss.do")
    public String discuss(Integer pageNo,ModelMap modelMap) {
        Pagination pagination = this.discussService.getPagea(
                SimplePage.cpn(pageNo), Constants.PAGE_SIZE);
        modelMap.addAttribute("pagination",pagination);
        modelMap.addAttribute("pagination",pagination);
        log.println("讨论");
        return "/welcome/discuss";
    }

    @RequestMapping("/discussdetail.do")
    public ModelAndView discuss(HttpServletRequest request, HttpServletResponse response) {
        ModelAndView mav = new ModelAndView("/discuss/discussdetail");
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
        System.err.println("简历");
        log.println("简历");
        return "welcome/aboutme";
    }
    @RequestMapping("/404.do")
    public String notfind() {
        return "/404";
    }
    @RequestMapping("/500.do")
    public String error() {
        return "/500";
    }
}
