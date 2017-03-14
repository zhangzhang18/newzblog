package com.zblog.service.impl;

import com.zblog.dao.MessageMapper;
import com.zblog.model.Message;
import com.zblog.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
@Service("MessageService")
public class MessageServiceImpl implements MessageService {
    @Autowired
    MessageMapper messageMapper;

    public List<Message> SelectAllMessage() {
        return messageMapper.SelectAllMessage();
    }
}
