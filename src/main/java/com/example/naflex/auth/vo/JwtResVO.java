package com.example.naflex.auth.vo;

import com.example.naflex.auth.member.user.User;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 *  JwtResVO의 역할
 *      -> 사용자에게 반환될 JWT을 담음
 * */
@Data
public class JwtResVO implements Serializable {

    private String jwttoken;
    private List<User> users;

    public JwtResVO(String jwttoken, List<User> users) {
        this.jwttoken = jwttoken;
        this.users = users;
    }

}
