package com.ccb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
    public List<Message> getMessagesByType(Integer userId,Integer messageType) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId)
                .eq("message_type", messageType)
                .orderByDesc("create_time"); // 根据 createTime 字段倒序排序;
        return messageMapper.selectList(queryWrapper);
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

    @Override
    public int countUnreadMessages(Integer userId) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("read", false);
        Long count = messageMapper.selectCount(queryWrapper);
        return count != null ? count.intValue() : 0;
    }

    @Override
    public void markMessagesAsReadById(Integer id) {
        Message message=messageMapper.selectById(id);
        message.setRead(true);
        messageMapper.updateById(message);
    }

    @Override
    public void markMessagesAsReadByType(Integer userId, Integer messageType) {
        QueryWrapper<Message> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId).eq("message_type", messageType);
        List<Message> messages = messageMapper.selectList(queryWrapper);
        for (Message message : messages) {
            message.setRead(true);
            messageMapper.updateById(message);
        }
    }
}