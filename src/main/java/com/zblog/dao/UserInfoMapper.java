package com.zblog.dao;

import com.zblog.model.UserInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInfoMapper {
    int deleteByPrimaryKey(Integer userid);

    int insert(UserInfo record);

    int insertSelective(UserInfo record);

    UserInfo selectByPrimaryKey(Integer userid);

    int updateByPrimaryKeySelective(UserInfo record);

    int updateByPrimaryKey(UserInfo record);

    UserInfo getUserById(Integer userid);
}