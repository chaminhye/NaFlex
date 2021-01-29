package com.example.naflex.test.controller;

import com.example.naflex.test.service.TestService;
import com.example.naflex.test.vo.TestVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(TestRestController.class)       // 테스트할 특정 컨트롤러 명시
@Slf4j
public class TestRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TestService testService;

    @Test
    public void getListTest() throws Exception{
        // given
        TestVO testVo = TestVO.builder()
                .id("mhcha")
                .name("민혜")
                .build();

        given(testService.selectOneMember("mhcha")).willReturn(testVo);

        // when
        final ResultActions actions = mockMvc.perform(get("/testValue2")
                                            .contentType(MediaType.APPLICATION_JSON))
                                            .andDo(print());


        //then
        actions
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name", is("GOD")))
                .andDo(print());

    }

}