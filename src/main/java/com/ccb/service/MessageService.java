package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Message;
import com.ccb.model.pojo.Preference;

import java.util.List;

public interface MessageService extends IService<Message> {
    List<Message> getAllMessages();
    Message getMessageById(Integer id);
    void createMessage(Message message);
    void updateMessage(Message message);
    void deleteMessage(Integer id);
}
