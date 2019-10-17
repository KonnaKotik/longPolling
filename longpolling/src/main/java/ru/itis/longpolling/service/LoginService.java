package ru.itis.longpolling.service;

import ru.itis.longpolling.dto.TokenDto;
import ru.itis.longpolling.form.LoginForm;

public interface LoginService {

    TokenDto login(LoginForm loginForm);

}
