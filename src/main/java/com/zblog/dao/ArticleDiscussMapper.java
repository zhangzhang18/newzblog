package com.zblog.dao;

import com.zblog.model.ArticleDiscuss;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleDiscussMapper {
    int deleteByPrimaryKey(Integer discussid);

    int insert(ArticleDiscuss record);

    int insertSelective(ArticleDiscuss record);

    ArticleDiscuss selectByPrimaryKey(Integer discussid);

    int updateByPrimaryKeySelective(ArticleDiscuss record);

    int updateByPrimaryKey(ArticleDiscuss record);
}