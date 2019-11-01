package ru.itis.longpolling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.longpolling.model.Token;
import ru.itis.longpolling.model.user.User;

import java.util.Optional;

public interface TokensRepository extends JpaRepository<Token, Long> {

    Optional<Token> findByValue(String value);

}
