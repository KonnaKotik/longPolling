package ru.itis.longpolling.form;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserCreateForm {

    private String hashPassword;
    private String email;

    private String firstName;
    private String lastName;
}
