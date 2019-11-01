package ru.itis.longpolling.security.detaile;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.itis.longpolling.model.Token;
import ru.itis.longpolling.repository.TokensRepository;
import ru.itis.longpolling.repository.UsersRepository;


import java.util.Optional;

@Service(value = "customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UsersRepository usersRepository;
    private final TokensRepository tokensRepository;

    @Autowired
    public UserDetailsServiceImpl (UsersRepository usersRepository, TokensRepository tokensRepository) {
        this.usersRepository = usersRepository;
        this.tokensRepository = tokensRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String value) throws UsernameNotFoundException {
        Optional<Token> authenticationCandidate = tokensRepository.findByValue(value);
        if (authenticationCandidate.isPresent()) {
            Token token = authenticationCandidate.get();
            return new UserDetailsImpl(token.getUser(), token);

        /*Optional<User> userOptional = usersRepository.findOneByEmail(email);
        if(userOptional.isPresent()) {
            Token token =
            return new UserDetailsImpl;*/
        } else {
            throw new SecurityException("User with email <" + value + "> not found");
        }
    }


}
