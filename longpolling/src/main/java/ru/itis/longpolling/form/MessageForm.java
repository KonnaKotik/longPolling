package ru.itis.longpolling.form;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.longpolling.dto.UserDto;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageForm {

    private String nameAuthor;

    private String value;
}
