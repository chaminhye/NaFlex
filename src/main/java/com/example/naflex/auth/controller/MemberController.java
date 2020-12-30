package com.example.naflex.auth.controller;

import com.example.naflex.auth.member.Member;
import com.example.naflex.auth.member.MemberRepository;
import com.example.naflex.auth.member.MemberVO;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequiredArgsConstructor
public class MemberController {


    @Autowired
    final MemberRepository memberRepository;
    @Autowired
    final PasswordEncoder encode;

    @GetMapping("/api/saveMember")
    public ResponseEntity<Member> saveMember(HttpServletRequest req,  @RequestBody Member member) {
        member.setPassword(encode.encode(member.getPassword()));
        return new ResponseEntity<Member>(memberRepository.save(member), HttpStatus.OK);
    }
}
