package ru.itis.longpolling.service;


import ru.itis.longpolling.form.MessageForm;

import java.util.List;

public interface MessageService {

    List<MessageForm> getAllMessage();

    void save(MessageForm messageForm);
}
