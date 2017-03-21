package com.zblog.service.impl;

import com.zblog.dao.UserMapper;
import com.zblog.model.User;
import com.zblog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2016/11/19.
 */
@Transactional
@Service("UserService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public List<User> SelectAll() {

        return userMapper.SelectAll();

    }

    public User SelectByUsername(String username) {

        return userMapper.SelectByUsername(username);
    }

    public User getUserPWDByUsername(String username) {
        return userMapper.getUserPWDByUsername(username);
    }

    public int insert(User user) {
        return userMapper.insert(user);
    }
}
