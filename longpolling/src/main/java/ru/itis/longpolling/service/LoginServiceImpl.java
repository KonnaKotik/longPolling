package ru.itis.longpolling.service;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.longpolling.dto.TokenDto;
import ru.itis.longpolling.form.LoginForm;
import ru.itis.longpolling.model.Token;
import ru.itis.longpolling.model.user.User;
import ru.itis.longpolling.repository.TokensRepository;
import ru.itis.longpolling.repository.UsersRepository;

import java.util.Optional;


@Service
public class LoginServiceImpl implements LoginService {


    private final UsersRepository usersRepository;

    private final TokensRepository tokensRepository;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginServiceImpl (UsersRepository usersRepository, TokensRepository tokensRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.tokensRepository = tokensRepository;
        this.usersRepository = usersRepository;
    }

    @Override
    public TokenDto login(LoginForm loginForm) {
        Optional<User> userCandidate = usersRepository.findOneByEmail(loginForm.getEmail());

        if(userCandidate.isPresent()) {
            User user = userCandidate.get();

            if(passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                Token token = Token.builder()
                        .user(user)
                        .value(RandomStringUtils.random(10, true, true))
                        .build();

                tokensRepository.save(token);
                return TokenDto.from(token);
            }
        } throw new BadCredentialsException("Incorrect login/password");
    }
}
