package ru.itis.longpolling.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.itis.longpolling.model.user.User;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String text;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
