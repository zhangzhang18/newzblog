package com.zblog.service;

import com.zblog.common.page.Pagination;
import com.zblog.model.Discuss;
import org.apache.ibatis.annotations.Param;

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

    List<Discuss> SelectHotDiscussByUid(Integer userid);

    Pagination getPage(@Param("begin")int begin, @Param("end")int end, @Param("vistor")int  vistor);

    int insertDiscuss(Discuss discuss);

    Pagination getPagea(@Param("begin")int begin, @Param("end")int end);
}
