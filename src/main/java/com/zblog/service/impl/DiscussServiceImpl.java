package com.zblog.service.impl;

import com.zblog.common.page.Pagination;
import com.zblog.dao.DiscussMapper;
import com.zblog.model.Discuss;
import com.zblog.service.DiscussService;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
@Transactional
@Service("DiscussService")
public class DiscussServiceImpl implements DiscussService{
    protected static final Log log = LogFactory.getLog(DiscussServiceImpl.class);
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

    public List<Discuss> SelectHotDiscussByUid(Integer userid) {
        return discussMapper.SelectHotDiscussByUid(userid);
    }

    public Pagination getPage( int pageNo,   int pageSize,  int vistor) {
        int total = 0;
        Pagination pagination = null;
        List<Discuss> users = null;
        ArticleServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize="
                + pageSize);
        int begin = (pageNo - 1) * pageSize;
        int end = begin + pageSize;
        users = this.discussMapper.getPage(begin, end, vistor);
        total = this.discussMapper.getCount(vistor);
        pagination = new Pagination(pageNo, pageSize, total, users);
        return pagination;
    }



    public int insertDiscuss(Discuss discuss) {
        return discussMapper.insertDiscuss(discuss);
    }

    public Pagination getPagea( int pageNo,   int pageSize) {
        int total = 0;
        Pagination pagination = null;
        List<Discuss> users = null;
        ArticleServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize="
                + pageSize);
        int begin = (pageNo - 1) * pageSize;
        int end = begin + pageSize;
        users = this.discussMapper.getPagea(begin, end);
        total = this.discussMapper.getCounta();
        pagination = new Pagination(pageNo, pageSize, total, users);
        return pagination;
    }
}
