package ru.itis.longpolling.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.longpolling.dto.TokenDto;
import ru.itis.longpolling.form.LoginForm;
import ru.itis.longpolling.service.LoginService;

import javax.validation.Valid;

@RestController
public class LoginController {


    private final LoginService loginService;

    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginForm loginForm) {
       return ResponseEntity.ok().body(loginService.login(loginForm));
    }
}
