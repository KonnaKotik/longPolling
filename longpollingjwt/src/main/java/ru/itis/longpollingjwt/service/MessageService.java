package ru.itis.longpollingjwt.service;




import ru.itis.longpollingjwt.form.MessageForm;

import java.util.List;

public interface MessageService {

    List<MessageForm> getAllMessage();

    MessageForm save(MessageForm messageForm);
}
