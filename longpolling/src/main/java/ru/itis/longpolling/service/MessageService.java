package ru.itis.longpolling.service;


import ru.itis.longpolling.form.MessageForm;

import java.util.List;

public interface MessageService {

    List<MessageForm> getAllMessage();

    MessageForm save(MessageForm messageForm, String tokenValue);
}
