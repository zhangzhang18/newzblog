package com.zblog.service;

import com.zblog.model.User;

import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
public interface UserService {

    List<User> SelectAll();

    User SelectByUsername(String username);

    User getUserPWDByUsername(String username);

    int insert(User user);
}
