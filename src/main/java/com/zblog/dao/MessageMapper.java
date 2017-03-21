package com.zblog.dao;

import com.zblog.model.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Message record);

    int insertSelective(Message record);

    Message selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Message record);

    int updateByPrimaryKey(Message record);

    List<Message> SelectAllMessage();

    List<Message> getPage(@Param("begin") int begin, @Param("end") int end, @Param("touserid") int touserid);

    int getCount(@Param("touserid") int touserid);
}