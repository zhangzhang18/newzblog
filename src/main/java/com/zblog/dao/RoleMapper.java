package com.zblog.dao;

import com.zblog.model.Role;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleMapper {
    int deleteByPrimaryKey(Integer roleid);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Integer roleid);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);
}