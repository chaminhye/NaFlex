package com.example.naflex.auth.member.user;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.*;

@Data
@AllArgsConstructor
@Entity(name="member_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name ="idx")
    private Long idx;

    @Column(name="username")
    private String username;

    @Column(name= "member_id")
    private Long memberId;

    public User(String username, Long memberId){
        this.username = username;
        this.memberId = memberId;
    }
}
