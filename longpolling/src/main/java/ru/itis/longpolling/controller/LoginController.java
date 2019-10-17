package ru.itis.longpolling.controller;


import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @ApiOperation("Login user")
    @PostMapping("/login")
    @PreAuthorize("permitAll()")
    public ResponseEntity<TokenDto> login(@Valid @RequestBody LoginForm loginForm) {
       return ResponseEntity.ok().body(loginService.login(loginForm));
    }
}
