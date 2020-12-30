package com.example.naflex.auth.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 *  JwtReqVO 역할
 *      -> 사용자에게 받은 username, password를 저장
 * */
//@JsonIgnoreProperties(ignoreUnknown =  true)
//@Accessors(chain=true)
@Data
public class JwtReqVO implements Serializable {
//    @JsonIgnore
    private String email;
//    @JsonIgnore
    private String password;

    //need default constructor for JSON Parsing
    public JwtReqVO() {   }

    public JwtReqVO(String email, String password) {
        this.setEmail(email);
        this.setPassword(password);
    }

}