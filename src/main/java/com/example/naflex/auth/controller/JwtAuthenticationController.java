package com.example.naflex.auth.controller;

import com.example.naflex.auth.config.JwtTokenUtil;
import com.example.naflex.auth.member.Member;
import com.example.naflex.auth.member.MemberRepository;
import com.example.naflex.auth.member.user.User;
import com.example.naflex.auth.member.user.UserRepository;
import com.example.naflex.auth.service.CookieUtil;
import com.example.naflex.auth.service.JwtUserDetailsService;
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
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.Cookie;
import java.util.ArrayList;
import java.util.List;


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
        logger.error("createAuthenticationToken::: member {}",authenticationRequest);

        final Member member = userDetailService.authenticateByEmailAndPassword
                (authenticationRequest.getEmail(), authenticationRequest.getPassword());

        // accessToken과 refreshToken을 생성성
       final String token = jwtTokenUtil.generateToken(member);
       final String refreshJwt = jwtTokenUtil.generateRefreshToken(member);

       List<User> userList = new ArrayList<>();
       // token이 정상적으로 발생되었다면, 사용자 리스트 조회하여 같이 반환
       if(!StringUtils.isEmpty(token)){
            userList = userRepository.findBymemberIdOrderBySorting(member.getIdx());
       }
        Cookie accessToken = cookieUtil.createCookie(jwtTokenUtil.ACCESS_TOKEN_NAME, token);
        Cookie refreshToekn = cookieUtil.createCookie(jwtTokenUtil.REFRESH_TOKEN_NAME, refreshJwt);

        // 이후
//        redisUtil.setDataExpire(refreshJwt, member.getUsername(), JwtUtil.REFRESH_TOKEN_VALIDATION_SECOND);
//        res.addCookie(accessToken);
//        res.addCookie(refreshToken);

        return ResponseEntity.ok(new JwtResVO(token, userList));
    }

    @GetMapping(value="/api/saveMember")
    public ResponseEntity<?> saveMember(@RequestParam(value="email", required = false) String email
                                        , @RequestParam(value="password", required = false) String password){
        logger.error("saveMember::: email {}",email);
        logger.error("saveMember::: password {}",password);
        String result = "ERROR";
        try{
            Member member = memberRepository.findByEmail(email).orElse(null);

            // 전달받은 email이 없는 경우만 회원가입
            if(member == null){
                Member memberData = new Member(email, encode.encode(password));
                Member newMember = memberRepository.save(memberData);
                // 기본 사용자 추가
                User user = new User("user", newMember.getIdx());
                userRepository.save(user);
                result = "OK";
            }
        }catch(Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(result, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        // 회원가입시 member, member_user에도 insert 기본사용자
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}

