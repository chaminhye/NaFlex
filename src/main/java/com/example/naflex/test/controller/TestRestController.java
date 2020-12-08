package com.example.naflex.test.controller;

import com.example.naflex.test.service.TestService;
import com.example.naflex.test.vo.TestVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestRestController {

    @Autowired
    private TestService testService;
    /**
     *      Spring 4.0 버전 이상부터
     *      - @Controller + @ResponseBody 대신 @RestController 이용가능
     *      - @RestController : @Response 추가할 필요 없이, 활성화 되어 있음
     *
     *       아래의 두가지 동일하게 사용할 수 있음
     *      - @GetMapping(value="/testValue")
     *      - @RequestMapping(value="/testValue", method= RequestMethod.GET)
     * */

    @GetMapping(value="/testValue")
//    @RequestMapping(value="/testValue", method= RequestMethod.GET)
    public String getTestValue(){
        return "RESTController test";
    }

    @GetMapping(value="/testValue2")
    public TestVO getTestValue2(@RequestParam String id){
        return testService.selectOneMember(id);
    }


}
