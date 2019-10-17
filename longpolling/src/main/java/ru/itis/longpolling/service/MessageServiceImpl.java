package ru.itis.longpolling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.longpolling.form.MessageForm;
import ru.itis.longpolling.mapper.MessageMapper;
import ru.itis.longpolling.repository.MessageRepository;

import java.util.List;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
    }

    @Override
    public List<MessageForm> getAllMessage() {
        return messageMapper.convertModelsToDtos(messageRepository.findAll());
    }
}
