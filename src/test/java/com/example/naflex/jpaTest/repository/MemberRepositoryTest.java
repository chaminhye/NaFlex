package com.example.naflex.jpaTest.repository;

import com.example.naflex.jpaTest.vo.MemberVO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import static org.assertj.core.api.BDDAssertions.then;

@DataJpaTest
@Transactional(propagation = Propagation.NOT_SUPPORTED)     // 트랜잭션 기능이 필요없게 설정
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)        // 실제 디비로 설정
class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    void findById() {
        List<MemberVO> member = memberRepository.findById("mhcha2");

        then(!member.isEmpty());
//        System.out.println("MemberRepositoryTest.findById : "+member);
        for(MemberVO vo :member){
            then("mhcha").isEqualTo(vo.getId());
            then("민혜").isEqualTo(vo.getName());
        }
    }
}