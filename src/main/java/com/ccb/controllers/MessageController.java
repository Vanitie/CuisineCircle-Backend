package com.ccb.controllers;

import com.ccb.common.R;
import com.ccb.model.pojo.Message;
import com.ccb.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageService messageService;

    @GetMapping("/all")
    public R<List<Message>> getAllMessages() {
        log.info("Fetching all messages");
        try {
            List<Message> messages = messageService.getAllMessages();
            return R.success(messages);
        } catch (Exception e) {
            log.error("Error fetching all messages: ", e);
            return R.error("服务器内部错误");
        }
    }

    @GetMapping("/{userId}/{messageType}")
    public R<List<Message>> getMessagesByType(@PathVariable Integer userId, @PathVariable Integer messageType) {
        log.info("Fetching messages for userId={} and messageType={}", userId, messageType);
        try {
            List<Message> messages = messageService.getMessagesByType(userId, messageType);
            return R.success(messages);
        } catch (Exception e) {
            log.error("Error fetching messages by type: ", e);
            return R.error("服务器内部错误");
        }
    }

    @GetMapping("/{id}")
    public R<Message> getMessageById(@PathVariable Integer id) {
        log.info("Fetching message with id={}", id);
        try {
            Message message = messageService.getMessageById(id);
            if (message != null) {
                return R.success(message);
            } else {
                return R.error("消息未找到");
            }
        } catch (Exception e) {
            log.error("Error fetching message by id: ", e);
            return R.error("服务器内部错误");
        }
    }

    @PostMapping("/create")
    public R<Void> createMessage(@RequestBody Message message) {
        log.info("Creating message: {}", message);
        try {
            messageService.createMessage(message);
            return R.success();
        } catch (Exception e) {
            log.error("Error creating message: ", e);
            return R.error("服务器内部错误");
        }
    }

    @PutMapping("/update/{id}")
    public R<Void> updateMessage(@PathVariable Integer id, @RequestBody Message message) {
        log.info("Updating message with id={}: {}", id, message);
        try {
            message.setId(id);
            messageService.updateMessage(message);
            return R.success();
        } catch (Exception e) {
            log.error("Error updating message: ", e);
            return R.error("服务器内部错误");
        }
    }

    @GetMapping("/unread/count/{userId}")
    public R<Integer> countUnreadMessages(@PathVariable Integer userId) {
        log.info("Counting unread messages for userId={}", userId);
        try {
            int count = messageService.countUnreadMessages(userId);
            return R.success(count);
        } catch (Exception e) {
            log.error("Error counting unread messages: ", e);
            return R.error("服务器内部错误");
        }
    }

    @PutMapping("/read/{id}")
    public R<Void> markMessagesAsReadById(@PathVariable Integer id) {
        log.info("Marking message as read with id={}", id);
        try {
            messageService.markMessagesAsReadById(id);
            return R.success();
        } catch (Exception e) {
            log.error("Error marking message as read by id: ", e);
            return R.error("服务器内部错误");
        }
    }

    @PutMapping("/read/{userId}/{messageType}")
    public R<Void> markMessagesAsReadByType(@PathVariable Integer userId, @PathVariable Integer messageType) {
        log.info("Marking messages as read for userId={} and messageType={}", userId, messageType);
        try {
            messageService.markMessagesAsReadByType(userId, messageType);
            return R.success();
        } catch (Exception e) {
            log.error("Error marking messages as read by type: ", e);
            return R.error("服务器内部错误");
        }
    }

    @DeleteMapping("/delete/{id}")
    public R<Void> deleteMessage(@PathVariable Integer id) {
        log.info("Deleting message with id={}", id);
        try {
            messageService.deleteMessage(id);
            return R.success();
        } catch (Exception e) {
            log.error("Error deleting message: ", e);
            return R.error("服务器内部错误");
        }
    }
}
