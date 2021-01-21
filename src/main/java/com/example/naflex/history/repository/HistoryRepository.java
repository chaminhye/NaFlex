package com.example.naflex.history.repository;

import com.example.naflex.history.vo.HistoryVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@EnableJpaRepositories
public interface HistoryRepository extends JpaRepository<HistoryVO, Long> {
    // 시청 히스토리 추가
    public HistoryVO save(HistoryVO vo);
    // 사용자_VOD 히스토리 확인
    public Optional<HistoryVO> findByUserIdxAndVodIdx(Long userIdx , Long vodIdx);

}
