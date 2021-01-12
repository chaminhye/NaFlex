package com.example.naflex.auth.member.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name="member_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, name ="user_idx")
    private Long userIdx;

    @Column(name="username")
    private String username;

    @Column(name= "member_id")
    private Long memberId;

    @Column(name= "sorting")
    private Long sorting;

    public User(String username, Long memberId){
        this.username = username;
        this.memberId = memberId;
    }
}
