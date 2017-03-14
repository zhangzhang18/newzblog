package com.zblog.dao;

import com.zblog.model.UserArticleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface UserArticleMapper {
    int deleteByPrimaryKey(UserArticleKey key);

    int insert(UserArticleKey record);

    int insertSelective(UserArticleKey record);
}