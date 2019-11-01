package ru.itis.longpolling.mapper;

import org.springframework.stereotype.Component;
import ru.itis.longpolling.dto.UserDto;
import ru.itis.longpolling.form.UserCreateForm;
import ru.itis.longpolling.model.user.User;
import ru.itis.longpolling.model.user.UserRole;
import ru.itis.longpolling.model.user.UserState;

@Component
public class UserMapper {

    public User convertCreateFormToModel (UserCreateForm userCreateForm) {
        return User.builder()
                .email(userCreateForm.getEmail())
                .hashPassword(userCreateForm.getPassword())
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

