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
@RequestMapping("/admin")
public class AdminController {
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
        List<Article> articleList=articleService.SelectNewArticleByUid(user.getUserid());
        List<Article> articlehot=articleService.SelectHotArticleByUid(user.getUserid());
        List<Discuss> discusshot=discussService.SelectHotDiscussByUid(user.getUserid());
        model.addAttribute("articleList",articleList);
        model.addAttribute("articlehot",articlehot);
        model.addAttribute("discusshot",discusshot);
        log.println("访问首页");
        System.err.println("访问首页");
        return "/user/index";
    }


}
