package com.example.naflex.test.controller;

import com.example.naflex.test.service.TestService;
import com.example.naflex.test.vo.TestVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class TestController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public TestService testService;

    @RequestMapping(value ="/home")
    public String home(){
        return "index.html";
    }

    @ResponseBody
    @RequestMapping(value = "/valueTest")
    public String valueTest(){
        return "테스트 String";
    }

    @RequestMapping("/test")
    public ModelAndView test() throws Exception {
        ModelAndView mav = new ModelAndView("test");

//        List<String> testList = new ArrayList<String>();
//        testList.add("a");
//        testList.add("b");
//        testList.add("c");
//
//        mav.setViewName("test");
//        mav.addObject("name", "mhcha");

        List<TestVO> testList = testService.selectTest();
        mav.addObject("list", testList);

        // log test
        logger.trace("TestController = trace level test");
        logger.debug("TestController = debug level test");
        logger.info("TestController = info level test");
        logger.warn("TestController = warn level test");
        logger.error("TestController = error level test");
        return mav;
    }

    @RequestMapping("/thymeleafTest")
    public String thymeleafTest(Model model){
        TestVO testVo = new TestVO("mhcha", "민혜");
        model.addAttribute("model", testVo);

        return "thymeleaf/thymeleafTest";
    }

}
