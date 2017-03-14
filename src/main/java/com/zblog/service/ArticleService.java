package com.zblog.service;

import com.zblog.model.Article;

import java.util.List;

/**
 * Created by hadoop01 on 16-11-29.
 */
public interface ArticleService {
    List<Article> getArticle();

    List<Article> getArticleByZcm();

    List<Article> SelectArticleByZcm();

    Article selectByPrimaryKey(int id);

    List<Article> SelectNewArticleByZcm();

    List<Article> SelectHotArticleByZcm();

    int addWcount(Article article);

    int insert(Article article);

    Article SelectArticleById(Integer userid);

    List<Article> SelectAllArticleByUId(Integer userid);

    int updateByPrimaryKey(Article article);
}
