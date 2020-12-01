package com.example.naflex.jpaTest.vo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MemberVOTest {

    /**
     * @Test Junit 사용법
     *      1) 테스트 클래스는 반드시 public으로 선언해야 한다.
     *      2) 클래스명은 관례상 테스트클래명 + Test 끝나는 이름으로 사용 한다.
     *      3) @Test 어노테이션을 선언한 메서드는 JUnit이 알아서 실행 할 수 있게 한다.
     * */
    @Test
    public void getId() {
        final MemberVO memberVO = MemberVO.builder()
                                          .id("mhcha")
                                          .name("민혜")
                                          .build();
        final String id = memberVO.getId();
        assertEquals("mhcha", id);
    }

    @Test
    public void getName(){
        final MemberVO memberVO = MemberVO.builder()
                                          .id("mhcha")
                                          .name("민혜")
                                          .build();
        final String name = memberVO.getName();
        assertEquals("차민혜", name);
    }
}