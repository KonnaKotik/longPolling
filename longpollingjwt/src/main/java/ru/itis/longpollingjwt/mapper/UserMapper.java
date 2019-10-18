package ru.itis.longpollingjwt.mapper;

import org.springframework.stereotype.Component;
import ru.itis.longpollingjwt.model.user.User;
import ru.itis.longpollingjwt.model.user.UserRole;
import ru.itis.longpollingjwt.model.user.UserState;
import ru.itis.longpollingjwt.dto.UserDto;
import ru.itis.longpollingjwt.form.UserCreateForm;

@Component
public class UserMapper {

    public User convertCreateFormToModel (UserCreateForm userCreateForm) {
        return User.builder()
                .email(userCreateForm.getEmail())
                .hashPassword(userCreateForm.getHashPassword())
                .firstName(userCreateForm.getFirstName())
                .lastName(userCreateForm.getLastName())
                .userRole(UserRole.USER)
                .userState(UserState.ACTIVE)
                .build();

    }

    public UserDto convertModelToDto(User model) {
        return UserDto.builder()
                .id(model.getId())
                .email(model.getEmail())
                .firstName(model.getFirstName())
                .lastName(model.getLastName())
                .build();
    }

    public User convertDtoToModel(UserDto userDto) {
        return User.builder()
                .id(userDto.getId())
                .email(userDto.getEmail())
                .firstName(userDto.getFirstName())
                .lastName(userDto.getLastName())
                .build();
    }
}

