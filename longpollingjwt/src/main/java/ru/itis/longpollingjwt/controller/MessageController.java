package ru.itis.longpollingjwt.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import ru.itis.longpollingjwt.form.MessageForm;
import ru.itis.longpollingjwt.service.MessageService;

import java.util.List;


@RestController
public class MessageController {

    private final MessageService messageService;
    private final MessageForm message = new MessageForm();

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @ApiOperation("Get message")
    @GetMapping("/messages")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<List<MessageForm>> getAllMessage(@RequestHeader("Authorization") String authorization) throws InterruptedException {

        if (message.getValue().isEmpty()) {
            message.wait();
        }
        message.clear();
        return ResponseEntity.ok().body(messageService.getAllMessage());

    }


    @ApiOperation("Add new message")
    @PostMapping("/messages")
    @PreAuthorize("isAuthenticated()")
    public ResponseEntity<Object> addMessage(@RequestHeader("Authorization") String authorization, @RequestBody MessageForm messageForm) {

        synchronized (message) {
            message.setNameAuthor(messageForm.getNameAuthor());
            message.setValue(messageForm.getValue());
            messageService.save(message);
        }

        return ResponseEntity.ok().build();

    }
}
