package ru.itis.longpollingjwt.service;


import ru.itis.longpollingjwt.form.UserCreateForm;

public interface UsersService {

    void signUp(UserCreateForm userCreateForm);
}
