package com.zblog.dao;

import com.zblog.model.UserRoleKey;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleMapper {
    int deleteByPrimaryKey(UserRoleKey key);

    int insert(UserRoleKey record);

    int insertSelective(UserRoleKey record);

    UserRoleKey getRoleByUserId(Integer userid);
}