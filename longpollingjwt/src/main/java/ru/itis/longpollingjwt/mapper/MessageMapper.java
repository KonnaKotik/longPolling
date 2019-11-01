package ru.itis.longpollingjwt.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.longpollingjwt.form.MessageForm;
import ru.itis.longpollingjwt.model.Message;
import ru.itis.longpollingjwt.repository.UsersRepository;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class MessageMapper {


    @Autowired
    private UsersRepository usersRepository;


    public Message convertMessageFormToMessage(MessageForm messageForm) {
        return Message.builder()
                .id(messageForm.getId())
                .text(messageForm.getValue())
                .user(usersRepository.findOneByEmail(messageForm.getNameAuthor()).orElseThrow(EntityNotFoundException::new))
                .build();
    }

    public MessageForm convertModelToForm(Message message) {
        return MessageForm.builder()
                .id(message.getId())
                .value(message.getText())
                .nameAuthor(message.getUser().getEmail())
                .build();
    }

    private Stream<MessageForm> modelsToForm(List<Message> messages) {
        return messages.stream().map(this::convertModelToForm);
    }

    public List<MessageForm> convertModelsToDtos(List<Message> messages){
        return modelsToForm(messages).collect(Collectors.toList());
    }
}
