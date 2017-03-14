package com.zblog.service;

import com.zblog.model.Discuss;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
public interface DiscussService {
    List<Discuss> SelectAllDiscuss();

    List<Discuss> SelectHotDiscussByZcm();

    Discuss selectByPrimaryKey(int id);

    List<Discuss> SelectReplyById(int id);

    int addWcount(Discuss discuss);

    int insert(Discuss discuss);
}
