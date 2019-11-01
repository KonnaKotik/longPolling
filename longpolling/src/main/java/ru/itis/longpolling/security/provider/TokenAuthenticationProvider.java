package ru.itis.longpolling.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import ru.itis.longpolling.model.Token;
import ru.itis.longpolling.repository.TokensRepository;
import ru.itis.longpolling.security.authentication.TokenAuthentication;
import ru.itis.longpolling.security.detaile.UserDetailsImpl;


import java.util.Optional;

@Component
public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private TokensRepository tokensRepository;

    @Qualifier("customUserDetailsService")
    @Autowired
    private UserDetailsService userDetailsService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        
        TokenAuthentication tokenAuthentication = (TokenAuthentication)authentication;

        Optional<Token> tokenCandidate = tokensRepository.findByValue(tokenAuthentication.getName());

        if (tokenCandidate.isPresent()) {
            UserDetailsImpl userDetails = (UserDetailsImpl)  userDetailsService.loadUserByUsername(tokenCandidate.get().getValue());
            tokenAuthentication.setUserDetails(userDetails);
            tokenAuthentication.setAuthenticated(true);
        } else throw new IllegalArgumentException("Bad token");
        
        return tokenAuthentication;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return TokenAuthentication.class.equals(authentication);
    }
}
