package com.lsj.board.domain.user;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class User{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    private String userId;

    private String password;

    @Builder
    public User(String name, String userId, String password) {
        this.name = name;
        this.userId = userId;
        this.password = password;
    }
}
