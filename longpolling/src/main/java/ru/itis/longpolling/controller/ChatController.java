package ru.itis.longpolling.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.longpolling.form.MessageForm;
import ru.itis.longpolling.service.MessageService;

import java.util.List;

@RestController
public class ChatController {

    private final MessageService messageService;

    @Autowired
    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/chat")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<MessageForm>> getAllMessage(@RequestHeader("Authorization") String authorization) {
        return ResponseEntity.ok().body(messageService.getAllMessage());

    }


    @PostMapping("/chat")
    @PreAuthorize("isAuthenticated()")
    public void addMessage(@RequestHeader("Authorization") String authorization, @RequestBody MessageForm messageForm) {


    }
}
