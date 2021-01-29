package com.example.naflex.history.controller;

import com.example.naflex.history.repository.HistoryRepository;
import com.example.naflex.history.service.HistorySerivce;
import com.example.naflex.history.vo.HistoryVO;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/history/*")
@CrossOrigin
@RequiredArgsConstructor
public class HistoryController {

    @Autowired
    private HistorySerivce historySerivce;

    @Autowired
    private HistoryRepository historyRepository;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @PostMapping(value = "/saveHistory", consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> saveContentHistory(@RequestBody HistoryVO req) throws Exception{
//        logger.error("saveContentHistory::: HistoryVO {}",req);
        HistoryVO res = historySerivce.saveHistory(req);
        return ResponseEntity.ok(res.getHistoryIdx());
    }

    @PostMapping(value ="/getHistoryList", consumes = MediaType.APPLICATION_JSON_VALUE )
    public ResponseEntity<?> getHistoryList(@RequestBody HistoryVO req) throws Exception{
        logger.error("getHistoryList::: HistoryVO {}",req);
        List<HistoryVO> res = historyRepository.findByUserIdxOrderByHistoryIdx(req.getUserIdx());
        return ResponseEntity.ok(res);
    }
}
