package com.zblog.controller;

import com.zblog.model.Article;
import com.zblog.model.User;
import com.zblog.service.ArticleService;
import com.zblog.util.DateUtil;
import com.zblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by hadoop01 on 16-11-29.
 */
@Controller
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping("/list.do")
    public ModelAndView show(){
        List<Article> articles=articleService.getArticle();
        ModelAndView mav = new ModelAndView("article/article");
        mav.addObject("articles",articles);
        return mav;
    }
    @RequestMapping("/zcmarticle.do")
    public ModelAndView zcm(){
        List<Article> articles=articleService.getArticleByZcm();
        ModelAndView mav = new ModelAndView("article/article");
        mav.addObject("articles",articles);
        return mav;
    }
    @RequestMapping("/addArticle.do")
    public ModelAndView add(){
        List<Article> articles=articleService.getArticleByZcm();
        ModelAndView mav = new ModelAndView("article/addarticle");
        mav.addObject("articles",articles);
        return mav;
    }
    @RequestMapping(value = "/addArticle.do",method = RequestMethod.POST)
    public ModelAndView addArticle(Article article,HttpServletRequest request, HttpServletResponse response){
        User nowuser  = UserUtil.getUser(request);
        if(nowuser!=null) {
            article.setCreateDatetime(new Date());
            article.setAuthor(nowuser.getUserid());
            int result = articleService.insert(article);
            List<Article> articleList = articleService.SelectArticleByZcm();
            ModelAndView mav = new ModelAndView("article/myarticle");
            mav.addObject("articleList", articleList);
            return mav;
        }else {
            ModelAndView mav = new ModelAndView("welcome/login");
            return mav;
        }

    }
    @RequestMapping("/myArticle.do")
    public ModelAndView add(HttpServletRequest request, HttpServletResponse response){
        User nowuser  = UserUtil.getUser(request);
        if(nowuser!=null) {
            List<Article> articleList = articleService.SelectAllArticleByUId(nowuser.getUserid());
            ModelAndView mav = new ModelAndView("article/myarticle");
            mav.addObject("articleList", articleList);
            return mav;
        }else {
            ModelAndView mav = new ModelAndView("welcome/login");
            return mav;
        }
    }
    @RequestMapping("/updateArticle.do")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
        User nowuser  = UserUtil.getUser(request);
        String id=request.getParameter("articleid");
        if(nowuser!=null) {
            Article articles = articleService.SelectArticleById(Integer.parseInt(id));
            ModelAndView mav = new ModelAndView("article/updatearticle");
            mav.addObject("articles", articles);
            return mav;
        }else {
            ModelAndView mav = new ModelAndView("welcome/login");
            return mav;
        }
    }
    @RequestMapping(value = "/updateArticle.do",method = RequestMethod.POST)
    public ModelAndView updatepost(Article article,HttpServletRequest request, HttpServletResponse response){
        User nowuser  = UserUtil.getUser(request);
        String id=request.getParameter("articleid");
        if(nowuser!=null) {
            article.setCreateDatetime(new Date());
           int i= articleService.updateByPrimaryKey(article);
            ModelAndView mav = new ModelAndView("article/myarticle");
            return mav;
        }else {
            ModelAndView mav = new ModelAndView("welcome/login");
            return mav;
        }
    }
}
