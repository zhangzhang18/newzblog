package com.zblog.service.impl;

import com.zblog.common.page.Pagination;
import com.zblog.dao.MessageMapper;
import com.zblog.model.Message;
import com.zblog.service.MessageService;
import org.apache.ibatis.logging.Log;
import org.apache.ibatis.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
@Service("MessageService")
public class MessageServiceImpl implements MessageService {
    protected static final Log log = LogFactory.getLog(MessageServiceImpl.class);
    @Autowired
    MessageMapper messageMapper;

    public List<Message> SelectAllMessage() {
        return messageMapper.SelectAllMessage();
    }

    public int insert(Message message) {
        return messageMapper.insert(message);
    }


    public Pagination getPage( int pageNo,   int pageSize,  int touserid) {
        int total = 0;
        Pagination pagination = null;
        List<Message> users = null;
        ArticleServiceImpl.log.debug("receive:pageNo=" + pageNo + " pageSize="
                + pageSize);
        int begin = (pageNo - 1) * pageSize;
        int end = begin + pageSize;
        users = this.messageMapper.getPage(begin, end, touserid);
        total = this.messageMapper.getCount(touserid);
        pagination = new Pagination(pageNo, pageSize, total, users);
        return pagination;
    }
}
