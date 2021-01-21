package com.example.naflex.history.controller;

import com.example.naflex.history.repository.HistoryRepository;
import com.example.naflex.history.vo.HistoryVO;
import lombok.RequiredArgsConstructor;
import org.h2.api.UserToRolesMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
//@RequestMapping("")
@CrossOrigin
@RequiredArgsConstructor
public class HistoryController {

    @Autowired
    private HistoryRepository historyRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/history/saveContentHistory", consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> saveContentHistory(@RequestBody HistoryVO req) throws Exception{
        logger.error("saveContentHistory::: HistoryVO {}",req);
        Optional<HistoryVO> vo = historyRepository.findByUserIdxAndVodIdx(req.getUserIdx(), req.getVodIdx());
        // 있는 경우 업데이트 확인
        vo.ifPresent( selectVo ->{
            selectVo.setVodIdx(req.getVodIdx());
            selectVo.setViewTime(req.getViewTime());
            selectVo.setHistoryIdx(req.getHistoryIdx());
            HistoryVO mod = historyRepository.save(selectVo);
            }
        );
        // 없는 경우 인서트 되는 거 찾아보기
        HistoryVO addVo = new HistoryVO();
        vo.isEmpty(addVo = historyRepository.save(req) );

        return ResponseEntity.ok(vo);
    }
}
