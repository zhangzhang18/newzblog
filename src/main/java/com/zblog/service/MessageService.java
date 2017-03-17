package com.zblog.service;

import com.zblog.common.page.Pagination;
import com.zblog.model.Message;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
public interface MessageService {
    List<Message> SelectAllMessage();

    int insert(Message message);

    Pagination getPage(@Param("begin")int begin, @Param("end")int end, @Param("touserid")int  touserid);
}
