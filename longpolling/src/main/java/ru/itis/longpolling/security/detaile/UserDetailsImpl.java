package ru.itis.longpolling.security.detaile;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.itis.longpolling.model.Token;
import ru.itis.longpolling.model.user.User;
import ru.itis.longpolling.model.user.UserState;

import java.util.Collection;
import java.util.Collections;

public class UserDetailsImpl implements UserDetails {

    private User user;
    private Token currentToken;

    public UserDetailsImpl(User user, Token token) {
        this.user = user;
        this.currentToken = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = user.getUserRole().name();
        GrantedAuthority authority = new SimpleGrantedAuthority(userRole);
        return Collections.singletonList(authority);
    }

    @Override
    public String getPassword() {
        return user.getHashPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !user.getUserState().equals(UserState.DELETED);
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
        // !user.getUserState().equals(UserState.BANNED);
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return user.getUserState().equals(UserState.ACTIVE );
    }

    public User getUser() {
        return user;
    }
}
