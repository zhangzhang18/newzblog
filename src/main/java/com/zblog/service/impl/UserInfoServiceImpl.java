package com.zblog.service.impl;

import com.zblog.dao.UserInfoMapper;
import com.zblog.model.UserInfo;
import com.zblog.service.UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by hadoop01 on 16-11-22.
 */
@Transactional
@Service("UserInfoService")
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper userInfoMapper;

    public UserInfo getUserById(Integer userid) {
        return userInfoMapper.getUserById(userid);
    }
}
