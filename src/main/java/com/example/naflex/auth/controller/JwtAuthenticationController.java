package com.example.naflex.auth.controller;

import com.example.naflex.auth.config.JwtTokenUtil;
import com.example.naflex.auth.member.Member;
import com.example.naflex.auth.service.JwtUserDetailsService;
import com.example.naflex.auth.vo.JwtReqVO;
import com.example.naflex.auth.vo.JwtResVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


/**
 *    JwtAuthenticationController의 역할
 *      -> 사용자가 입력한 email, password를 body에 넣어 POST API mapping/ authentication
 *      -> 사용자의 id,password 검증
 *      -> jwtTokenUtil을 호출하여 Token을 생성하고 JwtResVO Token을 담아 retrun ResponseEntity
 * */
@RestController
@CrossOrigin
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailService;

    @PostMapping("/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtReqVO authenticationRequest) throws Exception {
        final Member member = userDetailService.authenticateByEmailAndPassword
                (authenticationRequest.getEmail(), authenticationRequest.getPassword());
        final String token = jwtTokenUtil.generateToken(member.getEmail());
        return ResponseEntity.ok(new JwtResVO(token));
    }

}

