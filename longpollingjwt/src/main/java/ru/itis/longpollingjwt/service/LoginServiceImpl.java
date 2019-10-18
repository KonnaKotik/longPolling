package ru.itis.longpollingjwt.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.longpollingjwt.dto.TokenDto;
import ru.itis.longpollingjwt.form.LoginForm;
import ru.itis.longpollingjwt.model.user.User;
import ru.itis.longpollingjwt.repository.UsersRepository;
import ru.itis.longpollingjwt.security.details.UserDetailsImpl;

import javax.persistence.EntityNotFoundException;
import java.util.UUID;


@Service
public class LoginServiceImpl implements LoginService {


    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.prefix}")
    private String prefix;



    public LoginServiceImpl(UsersRepository usersRepository, PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
        this.usersRepository = usersRepository;
    }

    @Override
    public TokenDto login(LoginForm loginForm) {
        User user = usersRepository.findOneByEmail(loginForm.getEmail()).orElseThrow(EntityNotFoundException::new);

            if (passwordEncoder.matches(loginForm.getPassword(), user.getHashPassword())) {
                return TokenDto.builder().token(getTokenAsString(user)).build();
            } else {
                throw new BadCredentialsException("Incorrect login/password");
            }
    }

    private String getTokenAsString(User user) {
        return prefix + " " + Jwts.builder()
                .claim("rol", user.getUserRole().toString())
                .claim("email", user.getEmail())
                .setSubject(user.getId().toString())
                .setId(UUID.randomUUID().toString())
                .signWith(SignatureAlgorithm.HS512, jwtSecret).compact();
    }

    @Override
    public User getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        Long userId = ((UserDetailsImpl)authentication.getDetails()).getUser().getId();
        return usersRepository.findById(userId).orElseThrow(EntityNotFoundException::new);
    }


}
