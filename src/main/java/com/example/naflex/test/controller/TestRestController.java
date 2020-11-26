package com.example.naflex.test.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@RestController
public class TestRestController {

    /**
     *      Spring 4.0 버전 이상부터
     *      - @Controller + @ResponseBody 대신 @RestController 이용가능
     *      - @RestController : @Response 추가할 필요 없이, 활성화 되어 있음
     * */

    @RequestMapping(value="/testValue", method= RequestMethod.GET)
    public String getTestValue(){
        return "RESTController test";
    }


}
