package ru.itis.longpollingjwt.service;


import ru.itis.longpollingjwt.dto.TokenDto;
import ru.itis.longpollingjwt.form.LoginForm;
import ru.itis.longpollingjwt.model.user.User;

public interface LoginService {

    TokenDto login(LoginForm loginForm);

    User getCurrentUser();
}
