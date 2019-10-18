package ru.itis.longpollingjwt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.longpollingjwt.form.MessageForm;
import ru.itis.longpollingjwt.mapper.MessageMapper;
import ru.itis.longpollingjwt.repository.MessageRepository;


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
    public void save(MessageForm messageForm) {
        messageRepository.save(messageMapper.convertMessageFormToMessage(messageForm));
    }

    @Override
    public List<MessageForm> getAllMessage() {
        return messageMapper.convertModelsToDtos(messageRepository.findAll());
    }
}
