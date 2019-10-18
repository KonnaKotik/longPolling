package ru.itis.longpollingjwt.form;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MessageForm {

    private String nameAuthor;

    private String value;

    public void clear() {
        this.setValue(null);
        this.setNameAuthor(null);
    }
}