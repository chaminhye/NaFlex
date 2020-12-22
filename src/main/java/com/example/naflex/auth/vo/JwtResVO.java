package com.example.naflex.auth.vo;

import java.io.Serializable;

/**
 *  JwtResVO의 역할
 *      -> 사용자에게 반환될 JWT을 담음
 * */
public class JwtResVO implements Serializable {

    private static final long serialVersionUID = -8091879091924046844L;
    private final String jwttoken;

    public JwtResVO(String jwttoken) {
        this.jwttoken = jwttoken;
    }

    public String getToken() {
        return this.jwttoken;
    }
}
