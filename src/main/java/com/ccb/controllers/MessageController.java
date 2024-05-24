package com.ccb.controllers;

import com.ccb.common.R;
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
    public R<List<Message>> getAllMessages() {
        return R.success(messageService.getAllMessages());
    }

    @GetMapping("/{userId}/{messageType}")//根据消息类型选择对应的消息返回，1表示点赞，2表示评论，3表示关注，4表示待评价，且未读的在前面
    public R<List<Message>> getMessagesByType(@PathVariable Integer userId,@PathVariable Integer messageType) {
        return R.success(messageService.getMessagesByType(userId,messageType));
    }

    @GetMapping("/{id}")
    public R<Message> getMessageById(@PathVariable Integer id) {
        return R.success(messageService.getMessageById(id));
    }

    @PostMapping("/create")
    public void createMessage(@RequestBody Message message) {
        messageService.createMessage(message);
    }

    @PutMapping("/update/{id}")
    public R<Void> updateMessage(@PathVariable Integer id, @RequestBody Message message) {
        message.setId(id);
        messageService.updateMessage(message);
        return R.success();
    }

    @GetMapping("/unread/count")//获取未读消息数
    public R<Integer> countUnreadMessages(@RequestParam Integer userId) {
        return R.success(messageService.countUnreadMessages(userId));
    }

    @PutMapping("/read/{id}")//根据id设置已读
    public R<Void> markMessagesAsReadById(@PathVariable Integer id) {
        messageService.markMessagesAsReadById(id);
        return R.success();
    }

    @PutMapping("/read/{userId}/{messageType}")//根据消息类型设置已读
    public R<Void> markMessagesAsReadByType(@PathVariable Integer userId,@PathVariable Integer messageType) {
        messageService.markMessagesAsReadByType(userId,messageType);
        return R.success();
    }

    @DeleteMapping("/delete/{id}")
    public R<Void> deleteMessage(@PathVariable Integer id) {
        messageService.deleteMessage(id);
        return R.success();
    }
}
