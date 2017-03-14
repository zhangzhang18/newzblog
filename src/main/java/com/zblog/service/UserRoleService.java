package com.zblog.service;

import com.zblog.model.UserRoleKey;

/**
 * Created by hadoop01 on 16-11-22.
 */
public interface UserRoleService {
    UserRoleKey getRoleByUserId(Integer userid);
}
