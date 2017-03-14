package com.zblog.dao;

import com.zblog.model.Like;
import com.zblog.model.LikeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface LikeMapper {
    int deleteByPrimaryKey(LikeKey key);

    int insert(Like record);

    int insertSelective(Like record);

    Like selectByPrimaryKey(LikeKey key);

    int updateByPrimaryKeySelective(Like record);

    int updateByPrimaryKey(Like record);
}