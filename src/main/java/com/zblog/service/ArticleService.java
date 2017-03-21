package com.zblog.service;

import com.zblog.common.page.Pagination;
import com.zblog.model.Article;
import org.apache.ibatis.annotations.Param;

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

    int deleteByPrimaryKey(int id);

    List<Article> SelectNewArticleByUid(Integer userid);

    List<Article> SelectHotArticleByUid(Integer userid);

    Pagination getPagea(@Param("begin") int begin, @Param("end") int end, @Param("author") int author, @Param("articletype") int articletype);
    Pagination getPage(@Param("begin") int begin, @Param("end") int end, @Param("author") int author);

    int addLike(int i);

    List<Article> selectByTitle(String title);
}
