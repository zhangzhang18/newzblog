package com.zblog.service.impl;

import com.zblog.dao.ArticleMapper;
import com.zblog.model.Article;
import com.zblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hadoop01 on 16-11-29.
 */
@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    ArticleMapper articleMapper;

    public List<Article> getArticle() {
        return articleMapper.getArticle();
    }

    public List<Article> getArticleByZcm() {
        return articleMapper.getArticleByZcm();
    }

    public List<Article> SelectArticleByZcm() {
        return articleMapper.SelectArticleByZcm();
    }

    public Article selectByPrimaryKey(int id) {
        return articleMapper.selectByPrimaryKey(id);
    }

    public List<Article> SelectNewArticleByZcm() {
        return articleMapper.SelectNewArticleByZcm();
    }

    public List<Article> SelectHotArticleByZcm() {
        return articleMapper.SelectHotArticleByZcm() ;
    }

    public int addWcount(Article article) {
        return articleMapper.addWcount(article);
    }

    public int insert(Article article) {
        return articleMapper.insert(article);
    }

    public Article SelectArticleById(Integer userid) {
        return articleMapper.SelectArticleById(userid) ;
    }

    public List<Article> SelectAllArticleByUId(Integer userid) {
        return articleMapper.SelectAllArticleByUId( userid) ;
    }

    public int updateByPrimaryKey(Article article) {
        return articleMapper.updateByPrimaryKey(article);
    }
}
