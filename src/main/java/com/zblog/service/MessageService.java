package com.zblog.service;

import com.zblog.model.Message;

import java.util.List;

/**
 * Created by Administrator on 2017/3/12.
 */
public interface MessageService {
    List<Message> SelectAllMessage();
}
