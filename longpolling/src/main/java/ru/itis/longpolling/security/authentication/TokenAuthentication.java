package ru.itis.longpolling.security.authentication;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import ru.itis.longpolling.security.detaile.UserDetailsImpl;


import java.util.Collection;

public class TokenAuthentication implements Authentication {

    private boolean isAuthenticated;
    private UserDetailsImpl userDetails;
    private String token;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return userDetails.getAuthorities();
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public Object getDetails() {
        return userDetails;
    }

    @Override
    public Object getPrincipal() {

        if(userDetails != null) {
            return userDetails.getUser();
        } else return null;//выводить ошибку
    }

    @Override
    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    @Override
    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        this.isAuthenticated = isAuthenticated;

    }

    @Override
    public String getName() {
        return token;
    }

    public void setUserDetails(UserDetailsImpl userDetails) {
        this.userDetails = userDetails;
    }

    public void setToken(String token) {
        this.token = token;
    }
}


