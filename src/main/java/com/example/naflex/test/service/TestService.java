package com.example.naflex.test.service;

import com.example.naflex.test.mapper.TestMapper;
import com.example.naflex.test.vo.TestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TestService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TestMapper mapper;

    public List<TestVO> selectTest(){
        // log test
        logger.trace("TestService => trace level test");
        logger.debug("TestService =>debug level test");
        logger.info("TestService =>info level test");
        logger.warn("TestService =>warn level test");
        logger.error("TestService =>error level test");
        return mapper.selectTest();
    }

    public TestVO selectOneMember(String id){
        return mapper.selectOneMember(id);
    }
}
