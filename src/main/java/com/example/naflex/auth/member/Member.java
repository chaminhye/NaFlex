package com.example.naflex.auth.member;

import lombok.*;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(of = "uid")
@Entity(name="member")      // JPA가 관리하는 클래스
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(unique = true, name="email")
    private String email;

    @Column(name="password")
    private String password;

    public Member(String email, String password) {
        this.email = email;
        this.password = password;
    }
//
//    public static Member createMember(String email, String password) {
//        return new Member(email,password);
//    }
}
