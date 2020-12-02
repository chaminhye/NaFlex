package com.example.naflex.jpaTest.service;

import com.example.naflex.jpaTest.repository.MemberRepository;
import com.example.naflex.jpaTest.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.then;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@RestClientTest(MemberService.class)    //// 테스트 대상이 되는 빈을 주입받음
class MemberServiceTest {

    @MockBean
    private MemberRepository memberRepository;

    @Autowired
    private MockRestServiceServer mockRestServiceServer;

    @Autowired
    private MemberService memberService;

    @Test
    void getMember() {
        // 실제 api요청은 하지만, 응답은 test.json의 파일로 처리
        mockRestServiceServer.expect(requestTo("/memberTest/1"))
                              .andRespond(withSuccess(new ClassPathResource("/test.json", getClass()), MediaType.APPLICATION_JSON));

        MemberVO member = memberService.getMember(1L);

        // Junit4
        //assertThat("mhcha2").isEqualTo(member.getId());
        //assertThat("민혜").isEqualTo(member.getName());

        // Junit5 BDD
        then("mhcha2").isEqualTo(member.getId());
        then("민혜").isEqualTo(member.getName());
    }
}