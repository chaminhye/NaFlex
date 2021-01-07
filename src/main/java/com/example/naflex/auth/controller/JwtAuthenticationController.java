package com.example.naflex.auth.controller;

import com.example.naflex.auth.config.JwtTokenUtil;
import com.example.naflex.auth.member.Member;
import com.example.naflex.auth.member.MemberRepository;
import com.example.naflex.auth.member.user.UserRepository;
import com.example.naflex.auth.service.CookieUtil;
import com.example.naflex.auth.service.JwtUserDetailsService;
import com.example.naflex.auth.member.user.User;
import com.example.naflex.auth.vo.JwtReqVO;
import com.example.naflex.auth.vo.JwtResVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


/**
 *    JwtAuthenticationController의 역할
 *      -> 사용자가 입력한 email, password를 body에 넣어 POST API mapping/ authentication
 *      -> 사용자의 id,password 검증
 *      -> jwtTokenUtil을 호출하여 Token을 생성하고 JwtResVO Token을 담아 retrun ResponseEntity
 * */
@RestController
@CrossOrigin
@RequiredArgsConstructor
public class JwtAuthenticationController {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsService userDetailService;

    @Autowired
    final MemberRepository memberRepository;

    @Autowired
    final UserRepository userRepository;

    @Autowired
    final PasswordEncoder encode;

    @Autowired
    private CookieUtil cookieUtil;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtReqVO authenticationRequest) throws Exception {
        logger.error("::: member {}",authenticationRequest);

        final Member member = userDetailService.authenticateByEmailAndPassword
                (authenticationRequest.getEmail(), authenticationRequest.getPassword());

        // accessToken과 refreshToken을 생성성
       final String token = jwtTokenUtil.generateToken(member);
        final String refreshJwt = jwtTokenUtil.generateRefreshToken(member);

        Cookie accessToken = cookieUtil.createCookie(jwtTokenUtil.ACCESS_TOKEN_NAME, token);
        Cookie refreshToekn = cookieUtil.createCookie(jwtTokenUtil.REFRESH_TOKEN_NAME, refreshJwt);

        // 이후
//        redisUtil.setDataExpire(refreshJwt, member.getUsername(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
//        res.addCookie(accessToken);
//        res.addCookie(refreshToken);


        return ResponseEntity.ok(new JwtResVO(token));
    }

    @GetMapping("/api/saveMember")
    public ResponseEntity<Member> saveMember(HttpServletRequest req, @RequestBody Member member) {
        member.setPassword(encode.encode(member.getPassword()));
        Member mem = memberRepository.findByEmail(member.getEmail()).orElse(null);

        // 전달받은 email이 없는 경우만 회원가입
        if(mem == null){
            mem = memberRepository.save(member);
            // 기본 사용자 추가
            User user = new User("user", mem.getIdx());
            userRepository.save(user);
        }
        // 회원가입시 member, member_user에도 insert 기본사용자
        return new ResponseEntity<Member>(mem, HttpStatus.OK);
    }
}

