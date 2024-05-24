package com.ccb.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ccb.model.pojo.Message;

import java.util.List;

public interface MessageService extends IService<Message> {
    List<Message> getAllMessages();

    List<Message> getMessagesByType(Integer userId,Integer messageType);
    Message getMessageById(Integer id);
    void createMessage(Message message);//创建消息
    void updateMessage(Message message);
    void deleteMessage(Integer id);

    int countUnreadMessages(Integer userId);//返回未读消息数
    void markMessagesAsReadById(Integer id);
    void markMessagesAsReadByType(Integer userId, Integer messageType);
}
