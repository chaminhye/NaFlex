package com.example.naflex.auth.vo;

import lombok.Data;

import java.io.Serializable;

/**
 *  JwtResVO의 역할
 *      -> 사용자에게 반환될 JWT을 담음
 * */
@Data
public class JwtResVO implements Serializable {

    private final String jwttoken;

    public JwtResVO(String jwttoken) {
        this.jwttoken = jwttoken;
    }

}
