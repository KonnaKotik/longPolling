package ru.itis.longpolling.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.longpolling.form.MessageForm;
import ru.itis.longpolling.service.MessageService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MessageController {

    private final MessageService messageService;

    private final Map<String, List<MessageForm>> messages = new HashMap<>();

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ApiOperation("Get all message")
    @GetMapping("chat/getAll")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<MessageForm>> getAllMessage(@RequestHeader("Authorization") String authorization) {
        List<MessageForm> messageForms = new ArrayList<>(messageService.getAllMessage());
        return ResponseEntity.ok().body(messageForms);
    }

    @ApiOperation("Get message")
    @GetMapping("/chat")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<MessageForm>> getMessage(@RequestHeader("Authorization") String authorization) throws InterruptedException {

        synchronized (messages.get(authorization)) {
            if(messages.get(authorization).isEmpty()) {
                messages.get(authorization).wait();
            }
            List<MessageForm> response = new ArrayList<>(messages.get(authorization));
            messages.get(authorization).clear();
            return ResponseEntity.ok().body(response);
        }

    }


    @ApiOperation("Add new message")
    @PostMapping("/chat")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<MessageForm> addMessage(@RequestHeader("Authorization") String authorization, @RequestBody MessageForm messageForm) {

        MessageForm newMessage;

        if (!messages.containsKey(authorization)) {
            messages.put(authorization, new ArrayList<>());
        }

        for(List<MessageForm> messageForms: messages.values()) {
            synchronized (messageForms) {
                messageForms.add(messageForm);
                messageForms.notifyAll();
            }
        }
        newMessage = messageService.save(messageForm, authorization);


        return ResponseEntity.ok().body(newMessage);

    }
}
