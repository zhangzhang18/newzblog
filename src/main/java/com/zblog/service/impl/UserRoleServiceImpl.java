package com.zblog.service.impl;

import com.zblog.dao.UserRoleMapper;
import com.zblog.model.UserRoleKey;
import com.zblog.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by hadoop01 on 16-11-22.
 */
@Service("UserRoleService")
public class UserRoleServiceImpl implements UserRoleService {
    @Autowired
    UserRoleMapper userRoleMapper;
    public UserRoleKey getRoleByUserId(Integer userid) {
        return userRoleMapper.getRoleByUserId(userid);
    }
}
