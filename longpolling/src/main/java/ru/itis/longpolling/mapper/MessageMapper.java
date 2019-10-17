package ru.itis.longpolling.mapper;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.longpolling.form.MessageForm;
import ru.itis.longpolling.model.Message;
import ru.itis.longpolling.repository.UsersRepository;

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
                .text(messageForm.getValue())
                .user(usersRepository.findByFirstName(messageForm.getNameAuthor()).orElseThrow(EntityNotFoundException::new))
                .build();
    }

    public MessageForm convertModelToForm(Message message) {
        return MessageForm.builder()
                .value(message.getText())
                .nameAuthor(message.getUser().getFirstName())
                .build();
    }

    private Stream<MessageForm> modelsToForm(List<Message> messages) {
        return messages.stream().map(this::convertModelToForm);
    }

    public List<MessageForm> convertModelsToDtos(List<Message> messages){
        return modelsToForm(messages).collect(Collectors.toList());
    }
}
