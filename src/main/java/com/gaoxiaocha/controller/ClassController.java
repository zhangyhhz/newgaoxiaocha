package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONArray;
import com.gaoxiaocha.pojo.Classes;
import com.gaoxiaocha.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * ClassController
 *
 * @author zyh
 * @date 2020/5/6
 */
@Controller
public class ClassController {

    @Autowired
    private ClassService classService;

    @RequestMapping(value = "/class/student", method = RequestMethod.GET)
    @ResponseBody
    public String showClass(@RequestParam("stuNo") String stuNo) {
        JSONArray resultArray = new JSONArray();
        List<Classes> classes = classService.selectByStuNo(Integer.parseInt(stuNo));
        if (classes == null) {
            return "该同学不存在";
        }
        for (Classes aClass : classes) {
            resultArray.add(aClass);
        }
        return resultArray.toJSONString();
    }
}
