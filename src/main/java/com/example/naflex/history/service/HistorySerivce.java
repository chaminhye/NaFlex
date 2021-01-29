package com.example.naflex.history.service;

import com.example.naflex.history.repository.HistoryRepository;
import com.example.naflex.history.vo.HistoryVO;
import io.jsonwebtoken.lang.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@Service
public class HistorySerivce {

    @Autowired
    private HistoryRepository historyRepository;

    public HistoryVO saveHistory(HistoryVO vo) throws Exception{
       HistoryVO exist = historyRepository.findByUserIdxAndVodIdx(vo.getUserIdx(), vo.getVodIdx());
       HistoryVO result = null;

        if(exist == null){      // insert
            result = historyRepository.save(vo);
        }else{                  // update
            exist.setViewTime(vo.getViewTime());
            exist.setVodImg(vo.getVodImg());

            result = historyRepository.save(exist);
        }
        return result;
    }

}
