package com.zblog.dao;

import com.zblog.model.Article;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer articleid);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer articleid);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    List<Article> getArticle();

    List<Article> getArticleByZcm();

    List<Article> SelectArticleByZcm();

    List<Article> SelectNewArticleByZcm();

    List<Article> SelectHotArticleByZcm();

    int addWcount(Article article);

    List<Article> SelectAllArticleByUId(Integer userid);

    Article SelectArticleById(Integer userid);

    List<Article> SelectNewArticleByUid(Integer userid);

    List<Article> SelectHotArticleByUid(Integer userid);

    List<Article> getPage(@Param("begin")int begin, @Param("end") int end, @Param("author")int author);

    int getCount(@Param("author")int author);

    List<Article> getPagea(@Param("begin")int begin, @Param("end")int end,  @Param("author")int author,  @Param("articletype")int articletype);

    int getCounta( @Param("author")int author,  @Param("articletype")int articletype);

    int addLike(int id);
}