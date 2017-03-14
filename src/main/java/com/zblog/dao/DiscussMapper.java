package com.zblog.dao;

import com.zblog.model.Discuss;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscussMapper {
    int deleteByPrimaryKey(Integer discussid);

    int insert(Discuss record);

    int insertSelective(Discuss record);

    Discuss selectByPrimaryKey(Integer discussid);

    int updateByPrimaryKeySelective(Discuss record);

    int updateByPrimaryKeyWithBLOBs(Discuss record);

    int updateByPrimaryKey(Discuss record);

    List<Discuss> SelectAllDiscuss();

    List<Discuss> SelectHotDiscussByZcm();

    List<Discuss> SelectReplyById(int id);

    int addWcount(Discuss discuss);
}