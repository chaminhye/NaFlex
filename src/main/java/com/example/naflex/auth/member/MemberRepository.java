package com.example.naflex.auth.member;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface MemberRepository extends JpaRepository<Member, Long> {
    // 회원가입(member)
    public Member save(Member member);

    public Optional<Member> findByEmail(String email);


}
