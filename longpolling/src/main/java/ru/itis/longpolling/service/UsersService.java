package ru.itis.longpolling.service;

import ru.itis.longpolling.form.UserCreateForm;

public interface UsersService {

    void signUp(UserCreateForm userCreateForm);
}
