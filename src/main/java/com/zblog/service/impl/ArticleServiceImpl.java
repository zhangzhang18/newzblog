package com.zblog.service.impl;

import com.zblog.common.page.Pagination;
import com.zblog.dao.ArticleMapper;
import com.zblog.model.Article;
import com.zblog.service.ArticleService;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by hadoop01 on 16-11-29.
 */
@Transactional
@Service("ArticleService")
public class ArticleServiceImpl implements ArticleService {
    protected static final Log log = LogFactory.getLog(ArticleServiceImpl.class);
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

    public int deleteByPrimaryKey(int id) {
        return articleMapper.deleteByPrimaryKey(id);
    }

    public List<Article> SelectNewArticleByUid(Integer userid) {
        return articleMapper.SelectNewArticleByUid( userid);
    }

    public List<Article> SelectHotArticleByUid(Integer userid) {
        return articleMapper.SelectHotArticleByUid(userid);
    }

    public Pagination getPage( int pageNo,   int pageSize,  int author) {
        int total = 0;
        Pagination pagination = null;
        List<Article> users = null;
        ArticleServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize="
                + pageSize);
        int begin = (pageNo - 1) * pageSize;
        int end = begin + pageSize;
        users = this.articleMapper.getPage(begin, end, author);
        total = this.articleMapper.getCount(author);
        pagination = new Pagination(pageNo, pageSize, total, users);
        return pagination;
    }

    public Pagination getPagea( int pageNo,   int pageSize,  int author,int articletype) {
        int total = 0;
        Pagination pagination = null;
        List<Article> users = null;
        ArticleServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize="
                + pageSize);
        int begin = (pageNo - 1) * pageSize;
        int end = begin + pageSize;
        users = this.articleMapper.getPagea(begin, end, author,articletype);
        total = this.articleMapper.getCounta(author,articletype);
        pagination = new Pagination(pageNo, pageSize, total, users);
        return pagination;
    }

}
