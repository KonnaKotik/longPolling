package ru.itis.longpolling.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.longpolling.model.user.User;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

    Optional<User> findByFirstName(String firstName);

    boolean existsByEmail(String email);
}
