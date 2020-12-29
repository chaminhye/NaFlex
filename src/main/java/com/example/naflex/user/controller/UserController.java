package com.example.naflex.user.controller;

import com.example.naflex.auth.member.Member;
import com.example.naflex.auth.member.MemberRepository;
import com.example.naflex.user.vo.UserVO;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    final MemberRepository memberRepository;
    final PasswordEncoder encode;

    @PostMapping("/api/user")
    public String saveUser(@RequestBody UserVO userVO){
        memberRepository.save(Member.createMember(userVO.getEmail(), encode.encode(userVO.getPassword())));
        return "SUCCESS";
    }
}
