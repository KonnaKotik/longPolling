package ru.itis.longpollingjwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.longpollingjwt.model.Message;


public interface MessageRepository extends JpaRepository<Message, Long> {

    Message save(Message message);

}
