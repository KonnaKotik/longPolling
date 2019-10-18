package ru.itis.longpollingjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.itis.longpollingjwt.model.user.User;


import java.util.Optional;

@Repository
public interface UsersRepository extends JpaRepository<User, Long> {

    Optional<User> findOneByEmail(String email);

    Optional<User> findByFirstName(String firstName);

    boolean existsByEmail(String email);
}
