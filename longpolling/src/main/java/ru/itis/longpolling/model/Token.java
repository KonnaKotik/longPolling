package ru.itis.longpolling.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import ru.itis.longpolling.model.user.User;

import javax.persistence.*;

@AllArgsConstructor
@Builder
@Data
@NoArgsConstructor
@Entity
@ToString(exclude = "user")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String value;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private User user;
}
