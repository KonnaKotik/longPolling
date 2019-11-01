package ru.itis.longpolling.security.config;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import ru.itis.longpolling.security.filters.TokenAuthFilter;
import ru.itis.longpolling.security.provider.TokenAuthenticationProvider;

import java.util.Collections;


@EnableWebSecurity
@Configuration

public class SecurityConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private TokenAuthenticationProvider authenticationProvider;
    @Autowired
    private TokenAuthFilter tokenAuthFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().configurationSource(corsConfigurationSource())
                .and()
                .addFilterAfter(tokenAuthFilter, BasicAuthenticationFilter.class)
                .authenticationProvider(authenticationProvider);

        http.csrf().disable();


    }

    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration configuration = new CorsConfiguration().applyPermitDefaultValues();
        configuration.setAllowedMethods(Collections.singletonList("*"));
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
