package ru.itis.longpolling.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.longpolling.form.MessageForm;
import ru.itis.longpolling.mapper.MessageMapper;
import ru.itis.longpolling.model.Message;
import ru.itis.longpolling.model.Token;
import ru.itis.longpolling.repository.MessageRepository;
import ru.itis.longpolling.repository.TokensRepository;


import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;
    private final MessageMapper messageMapper;
    private final TokensRepository tokensRepository;

    @Autowired
    public MessageServiceImpl(MessageRepository messageRepository, MessageMapper messageMapper, TokensRepository tokensRepository) {
        this.messageRepository = messageRepository;
        this.messageMapper = messageMapper;
        this.tokensRepository = tokensRepository;
    }

    @Override
    public MessageForm save(MessageForm messageForm, String tokenValue) {
        Optional<Token> tokenCandidate = tokensRepository.findByValue(tokenValue);
        if(tokenCandidate.isPresent()) {
            String userEmail = tokenCandidate.get().getUser().getEmail();
            messageForm.setNameAuthor(userEmail);
        } else throw new IllegalArgumentException("Bad token");
        Message message = messageRepository.save(messageMapper.convertMessageFormToMessage(messageForm));
        return messageMapper.convertModelToForm(message);
    }

    @Override
    public List<MessageForm> getAllMessage() {
        return messageMapper.convertModelsToDtos(messageRepository.findAll());
    }
}
