package com.example.naflex.jpaTest.vo;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Junit test를 위한 라이프 사이클 확인 클래스 ( Junint 5 기준)")
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

    @BeforeAll      // (static)모든 테스트 실행 전 최초 한번 실행 (Junit4 : @BeforeClass)
    static void initializeBeforeAll(){
        System.out.println("intializeBeforeAll ...");
    }

    @BeforeEach     // 테스트 실행할때마다 테스트 전에 실행 (Junit4 : @Before)
    void initializeBeforeEach(){
        System.out.println("initializeBeforeEach ...");
    }

    @Test
    @DisplayName("@Test를 사용하는 first test")      // test 이름 설정
    void firstTest(){
        System.out.println("firstTest...");
        assertTrue(true);
    }

    @Test
    @DisplayName("@Test를 사용하는 second test")
    void secondTest(){
        assertTrue(true);
        System.out.println("secondTest...");
        assertNotEquals(1,2,"");
    }

    @Test
    @Disabled       //테스트를 수행하지 않고 패스 (Junit4 : @Ignore)
    @DisplayName("@Test를 사용하는 second test")
    void disabledTest(){
        System.out.println("disabled...");
    }

    @AfterEach      // 테스트 종료할때마다 테스트 이후 실행 (Junit4 : @After)
    void afterEach(){
        System.out.println("afterEach...");
    }

    @AfterAll      // (static)모든 테스트 종료 후 마지막 실행 (Junit4 : @AfterClass)
    static void afterAll(){
        System.out.println("afterAll...");
    }

}