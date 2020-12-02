package com.example.naflex.test.mapper;

import com.example.naflex.test.vo.TestVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
@Mapper
public interface TestMapper {

    List<TestVO> selectTest();
    TestVO selectOneMember(String id);
}
