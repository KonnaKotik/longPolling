package ru.itis.longpolling.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.itis.longpolling.form.MessageForm;
import ru.itis.longpolling.model.Message;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Component
public class MessageMapper {

    @Autowired
    private UserMapper userMapper;


    public Message convertMessageFormToMessage(MessageForm messageForm) {
        return Message.builder()
                .text(messageForm.getValue())
                .user(userMapper.convertDtoToModel(messageForm.getAuthor()))
                .build();
    }

    public MessageForm convertModelToForm(Message message) {
        return MessageForm.builder()
                .value(message.getText())
                .author(userMapper.convertModelToDto(message.getUser()))
                .build();
    }

    private Stream<MessageForm> modelsToForm(List<Message> messages) {
        return messages.stream().map(this::convertModelToForm);
    }

    public List<MessageForm> convertModelsToDtos(List<Message> messages){
        return modelsToForm(messages).collect(Collectors.toList());
    }
}
