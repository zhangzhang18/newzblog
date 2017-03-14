package com.zblog.service.impl;

import com.zblog.dao.DiscussMapper;
import com.zblog.model.Discuss;
import com.zblog.service.DiscussService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
@Service("DiscussService")
public class DiscussServiceImpl implements DiscussService{
    @Autowired
    DiscussMapper discussMapper;
    public List<Discuss> SelectAllDiscuss() {
        return discussMapper.SelectAllDiscuss();
    }

    public List<Discuss> SelectHotDiscussByZcm() {
        return discussMapper.SelectHotDiscussByZcm() ;
    }

    public Discuss selectByPrimaryKey(int id) {
        return discussMapper.selectByPrimaryKey(id);
    }

    public List<Discuss> SelectReplyById(int id) {
        return discussMapper.SelectReplyById(id);
    }

    public int addWcount(Discuss discuss) {
        return discussMapper.addWcount(discuss);
    }

    public int insert(Discuss discuss) {
        return discussMapper.insert(discuss);
    }
}
