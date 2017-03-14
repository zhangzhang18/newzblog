package com.zblog.dao;

import com.zblog.model.Notice;
import com.zblog.model.NoticeKey;
import org.springframework.stereotype.Repository;

@Repository
public interface NoticeMapper {
    int deleteByPrimaryKey(NoticeKey key);

    int insert(Notice record);

    int insertSelective(Notice record);

    Notice selectByPrimaryKey(NoticeKey key);

    int updateByPrimaryKeySelective(Notice record);

    int updateByPrimaryKeyWithBLOBs(Notice record);

    int updateByPrimaryKey(Notice record);
}