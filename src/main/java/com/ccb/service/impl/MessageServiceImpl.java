package com.ccb.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccb.mapper.MessageMapper;
import com.ccb.model.pojo.Message;
import com.ccb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper,Message> implements MessageService {

    @Autowired
    private MessageMapper messageMapper;

    @Override
    public List<Message> getAllMessages() {
        return messageMapper.selectList(null);
    }

    @Override
    public Message getMessageById(Integer id) {
        return messageMapper.selectById(id);
    }

    @Override
    public void createMessage(Message message) {
        messageMapper.insert(message);
    }

    @Override
    public void updateMessage(Message message) {
        messageMapper.updateById(message);
    }

    @Override
    public void deleteMessage(Integer id) {
        messageMapper.deleteById(id);
    }
}