package com.example.naflex.jpaTest.controller;

import com.example.naflex.jpaTest.repository.MemberRepository;
import com.example.naflex.jpaTest.service.MemberService;
import com.example.naflex.jpaTest.vo.MemberVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.transaction.Transactional;
import java.util.Optional;
import static org.mockito.BDDMockito.given;

import static org.assertj.core.api.BDDAssertions.then;
import static org.mockito.BDDMockito.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.iterableWithSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//@RunWith(SpringRunner.class)      // Junint4
@SpringBootTest(
        properties = {
                "testId=mhcha",
                "testName=민혜"
        }
        ,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@Transactional
@AutoConfigureMockMvc
@Slf4j
public class TestJpaRestControllerTest {

    @Value("${testId}")
    private String testId;

    @Value("${testName}")
    private String testName;

    @Autowired
    MockMvc mvc;

    @Autowired
    private TestRestTemplate restTemplate;

    // Service 등록
    @Autowired
    private MemberService memberService;

    @Autowired
    private WebApplicationContext ctx;

    @MockBean
    private MemberRepository memberRepository;

    @BeforeEach        // 테스트 실행할때마다 테스트 전에 실행 (Junit4 : @Before)
    void setUp() {
        this.mvc = MockMvcBuilders.webAppContextSetup(ctx)
                .addFilters(new CharacterEncodingFilter("UTF-8", true))         // 필터추가 : 한글
                .alwaysDo(print())
                .build();
    }

    @Test
    void getMember() throws Exception{
//        log.info("##### Properties 테스트 #####");
//        log.info("testId : "+testId);
//        log.info("testName : "+testName);

        /******** START : MOC MVC test **********/
//        log.info("******** START : MOC MVC test **********");
//        mvc.perform(get("/memberTest/1"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id", is("mhcha")))
//                .andDo(print());
//        log.info("******** END : MOC MVC test **********");
        /******** END : MOC MVC test **********/

        /******** START : TestRestTemplate test **********/
//        log.info("******** START : TestRestTemplate test **********");
//        ResponseEntity<MemberVO> response = restTemplate.getForEntity("/memberTest/1", MemberVO.class);
//        then(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        then(response.getBody()).isNotNull();
//        log.info("******** END : TestRestTemplate test **********");
        /******** END : TestRestTemplate test **********/

        /******** START : MockBean test **********/
        log.info("******** START : MockBean test **********");
        // given
       MemberVO memberVo = MemberVO.builder()
                                        .id("mhcha2")
                                        .name("민혜")
                                        .build();

        given(memberRepository.findById(1L)).willReturn(Optional.of(memberVo));


        // when
        Optional<MemberVO> member = memberService.findById(1L);
        if (member.isPresent()) {
            // ※ Junit4 사용시
            // assertThat(memberVo.getId()).isEqualTo(member.get().getId());
            // assertThat(memberVo.getName()).isEqualTo(member.get().getName());

            // Junit5 BDD 사용시
            // then
            then("mhcha").isEqualTo(member.get().getId());
            then("민혜").isEqualTo(member.get().getName());
        }
        log.info("******** END : MockBean test **********");
        /******** END : MockBean test **********/
    }
}