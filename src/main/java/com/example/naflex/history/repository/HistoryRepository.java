package com.example.naflex.history.repository;

import com.example.naflex.auth.member.user.User;
import com.example.naflex.history.vo.HistoryVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface HistoryRepository extends JpaRepository<HistoryVO, Long> {
    // 시청 히스토리 추가
    public HistoryVO save(HistoryVO vo);
    // 사용자_VOD 히스토리 확인
    public HistoryVO findByUserIdxAndVodIdx(Long userIdx , Long vodIdx);
    // 사용자 시청목록 리스트
    public List<HistoryVO> findByUserIdxOrderByHistoryIdx(Long userIdx);
}
