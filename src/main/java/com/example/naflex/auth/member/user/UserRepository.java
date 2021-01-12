package com.example.naflex.auth.member.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface UserRepository extends JpaRepository<User, Long> {
    // 사용자(member_user) 추가
    public User save(User user);
    // 사용자 리스트 조회
    public List<User> findBymemberIdOrderBySorting(Long memberId);
}
