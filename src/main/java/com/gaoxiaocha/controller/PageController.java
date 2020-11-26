package com.gaoxiaocha.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author:xiongwei
 * @Date:2020/11/16-23:01
 * @Description:com.gaoxiaocha.controller
 * @version:1.0
 */
@Controller
public class PageController {
    @GetMapping("/index")
    public String index(){
        return "gaoxiaocha";
    }
}
