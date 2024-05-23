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

    @GetMapping
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }

    @GetMapping("/{id}")
    public Message getMessageById(@PathVariable Integer id) {
        return messageService.getMessageById(id);
    }

    @PostMapping
    public void createMessage(@RequestBody Message message) {
        messageService.createMessage(message);
    }

    @PutMapping("/update/{id}")
    public void updateMessage(@PathVariable Integer id, @RequestBody Message message) {
        message.setId(id);
        messageService.updateMessage(message);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteMessage(@PathVariable Integer id) {
        messageService.deleteMessage(id);
    }
}
