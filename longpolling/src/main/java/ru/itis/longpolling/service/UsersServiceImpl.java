package ru.itis.longpolling.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.itis.longpolling.form.UserCreateForm;
import ru.itis.longpolling.mapper.UserMapper;
import ru.itis.longpolling.model.user.User;
import ru.itis.longpolling.repository.UsersRepository;

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
            userCreateForm.setHashPassword(passwordEncoder.encode(userCreateForm.getHashPassword()));
            User user = userMapper.convertCreateFormToModel(userCreateForm);
            usersRepository.save(user);
        } else {
            throw new BadCredentialsException("Login is already used");
        }
    }
}
