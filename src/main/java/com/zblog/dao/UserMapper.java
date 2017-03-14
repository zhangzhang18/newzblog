package com.zblog.dao;

import com.zblog.model.User;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    List<User> SelectAll();
    User SelectByUsername(String username);

    User getUserPWDByUsername(String username);
}