package com.ccb.controllers;

import com.ccb.model.pojo.Message;
import com.ccb.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{userId}/{messageType}")//根据消息类型选择对应的消息返回，1表示点赞，2表示评论，3表示待评价
    public List<Message> getMessagesByType(@PathVariable Integer userId,@PathVariable Integer messageType) {
        return messageService.getMessagesByType(userId,messageType);
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Integer id) {
        return messageService.getMessageById(id);
    }

    @PostMapping("/create")
    public void createMessage(@RequestBody Message message) {
        messageService.createMessage(message);
    }

    @PutMapping("/update/{id}")
    public void updateMessage(@PathVariable Integer id, @RequestBody Message message) {
        message.setId(id);
        messageService.updateMessage(message);
    }

    @GetMapping("/unread/count")//获取未读消息数
    public int countUnreadMessages(@RequestParam Integer userId) {
        return messageService.countUnreadMessages(userId);
    }

    @PutMapping("/read/{id}")//根据id设置已读
    public void markMessagesAsReadById(@PathVariable Integer id) {
        messageService.markMessagesAsReadById(id);
    }

    @PutMapping("/read/{userId}/{messageType}")//根据消息类型设置已读
    public void markMessagesAsReadByType(@PathVariable Integer userId,@PathVariable Integer messageType) {
        messageService.markMessagesAsReadByType(userId,messageType);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMessage(@PathVariable Integer id) {
        messageService.deleteMessage(id);
    }
}
