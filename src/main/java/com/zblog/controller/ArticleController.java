package com.zblog.controller;

import com.zblog.common.page.Pagination;
import com.zblog.common.page.SimplePage;
import com.zblog.model.Article;
import com.zblog.model.User;
import com.zblog.service.ArticleService;
import com.zblog.util.Constants;
import com.zblog.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.runtime.regexp.joni.Config.log;

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
    public String addArticle(Article article,HttpServletRequest request, HttpServletResponse response){
        User nowuser  = UserUtil.getUser(request);
        if(nowuser!=null) {
            article.setCreateDatetime(new Date());
            article.setAuthor(nowuser.getUserid());
            article.setWcount(0);
            int result = articleService.insert(article);
            return "redirect:/article/myArticle.do";
        }else {
            ModelAndView mav = new ModelAndView("welcome/login");
            return "redirect:/welcome/login.do";
        }

    }
    @RequestMapping("/article.do")
    public String add(HttpServletRequest request, HttpServletResponse response,Integer pageNo,ModelMap modelMap){
        User nowuser  = UserUtil.getUser(request);
        String type=request.getParameter("type");
        if(nowuser!=null) {
            if (type==null){
                Pagination pagination = this.articleService.getPage(
                        SimplePage.cpn(pageNo), Constants.PAGE_SIZE, nowuser.getUserid());
                modelMap.addAttribute("pagination",pagination);
                return "article/article";
            }else{
            Pagination pagination = this.articleService.getPagea(
                    SimplePage.cpn(pageNo), Constants.PAGE_SIZE, nowuser.getUserid(),Integer.parseInt(type));
            modelMap.addAttribute("pagination",pagination);
            return "article/article";
            }
        }else {
            return "redirect:/welcome/login.do";
        }
    }
    @RequestMapping("/updateArticle.do")
    public ModelAndView update(HttpServletRequest request, HttpServletResponse response){
        User nowuser  = UserUtil.getUser(request);
        String id=request.getParameter("articleid");
        if(nowuser!=null) {
            Article article = articleService.selectByPrimaryKey(Integer.parseInt(id));
            ModelAndView mav = new ModelAndView("article/updatearticle");
            mav.addObject("article", article);
            return mav;
        }else {
            ModelAndView mav = new ModelAndView("welcome/login");
            return mav;
        }
    }
    @RequestMapping(value = "/updateArticle.do",method = RequestMethod.POST)
    public String updatepost(Article article,HttpServletRequest request, HttpServletResponse response){
        User nowuser  = UserUtil.getUser(request);
        if(nowuser!=null) {
            article.setUpdateDatetime(new Date());
            int i= articleService.updateByPrimaryKey(article);
            return "redirect:/article/articledetail.do?articleid="+article.getArticleid();
        }else {
            return "redirect:/welcome/login.do";
        }
    }
    @RequestMapping(value = "/articledetail.do")
    public ModelAndView articledetail(HttpServletRequest request, HttpServletResponse response) {
        String id=request.getParameter("articleid");
        Article article=articleService.selectByPrimaryKey(Integer.parseInt(id));
        articleService.addWcount(article);
        ModelAndView mav=new ModelAndView("article/articledetail");
        mav.addObject("article",article);
        log.println("文章内容");
        System.err.println("文章内容");
        return mav;
    }
    @RequestMapping("/myArticle.do")
    public String my(HttpServletRequest request, HttpServletResponse response,Integer pageNo,ModelMap modelMap){
        User nowuser  = UserUtil.getUser(request);
        String type=request.getParameter("type");
        if(nowuser!=null) {
            Pagination pagination = this.articleService.getPage(
                    SimplePage.cpn(pageNo), Constants.PAGE_SIZE, nowuser.getUserid());
            modelMap.addAttribute("pagination",pagination);
            return "article/article";
        }else {
            return "redirect:/welcome/login.do";
        }
    }


    @RequestMapping("/delete.do")
    public String delete(HttpServletRequest request, HttpServletResponse response){
        User nowuser  = UserUtil.getUser(request);
        String id=request.getParameter("articleid");
        if(nowuser!=null) {
            articleService.deleteByPrimaryKey(Integer.parseInt(id));
            return "redirect:/article/myArticle.do";
        }else {
            return "redirect:/welcome/login.do";
        }
    }
    @RequestMapping(value = "/addlike.do",method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object>  addliike(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("articleid");
        Map<String,Object> map = new HashMap<String,Object>();
        articleService.addLike(Integer.parseInt(id));
        Article article=articleService.selectByPrimaryKey(Integer.parseInt(id));
        Map<String, Object> modelMap = new HashMap<String, Object>(1);
        modelMap.put("article", article);
        return modelMap;
    }
}
