package com.zblog.dao;

import com.zblog.model.ArticleLike;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleLikeMapper {
    int deleteByPrimaryKey(Integer likeid);

    int insert(ArticleLike record);

    int insertSelective(ArticleLike record);

    ArticleLike selectByPrimaryKey(Integer likeid);

    int updateByPrimaryKeySelective(ArticleLike record);

    int updateByPrimaryKey(ArticleLike record);
}