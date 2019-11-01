package ru.itis.longpollingjwt.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.longpollingjwt.form.UserCreateForm;
import ru.itis.longpollingjwt.mapper.UserMapper;
import ru.itis.longpollingjwt.model.user.User;
import ru.itis.longpollingjwt.repository.UsersRepository;


@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository usersRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;



    @Autowired
    public UsersServiceImpl(UsersRepository usersRepository, UserMapper userMapper, PasswordEncoder passwordEncoder) {
        this.usersRepository = usersRepository;
        this.userMapper = userMapper;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void signUp(UserCreateForm userCreateForm) {
        if(!usersRepository.existsByEmail(userCreateForm.getEmail())) {
            userCreateForm.setPassword(passwordEncoder.encode(userCreateForm.getPassword()));
            User user = userMapper.convertCreateFormToModel(userCreateForm);
            usersRepository.save(user);
        } else {
            throw new BadCredentialsException("Login is already used");
        }
    }
}
