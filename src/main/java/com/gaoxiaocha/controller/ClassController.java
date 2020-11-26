package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.pojo.Classes;
import com.gaoxiaocha.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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


    /**
     * 课表查询
     * @param stuNo
     * @return
     */
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
    
    @RequestMapping(value = "/class/charu",method = RequestMethod.POST)
    @ResponseBody
    public String charu(@RequestBody Classes classes){
        Result result =new Result();
        if(classService.insert(classes)==1) {
            result.setMsg("插入成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        }else {
            result.setMsg("插入失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/class/chazhao",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String chazhao(){
        Result<List<Classes>> result =new Result();
        List<Classes> list = classService.select();
        result.setData(list);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/class/gengxin",method = RequestMethod.POST)
    @ResponseBody
    public String gengxin(@RequestBody Classes classes){
        Result result =new Result();
        if(classService.update(classes)==1) {
            result.setMsg("更新成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        }else {
            result.setMsg("更新失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/class/shanchu",method = RequestMethod.POST)
    @ResponseBody
    public String shanchu(@RequestBody Classes classes){
        Result result =new Result();
        if(classService.delete(classes)==1) {
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        }else {
            result.setMsg("删除失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/class/fenyechazhao",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String fenyechazhao(@RequestParam("currentPage") int currentPage,@RequestParam("numPerPage") int numperPage){
        Result<List<List<String>>> result =new Result();
        List<Classes> list = classService.queryForPage(currentPage,numperPage);
        List<List<String>> lists = new ArrayList<>();
        for (Classes item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getId()));
            l.add(String.valueOf(item.getStuNo()));
            l.add(String.valueOf(item.getClassType()));
            l.add(String.valueOf(item.getClassName()));
            l.add(String.valueOf(item.getClassCode()));
            l.add(String.valueOf(item.getClassCategory()));
            l.add(String.valueOf(item.getClassStatus()));
            l.add(String.valueOf(item.getClassTeacher()));
            l.add(String.valueOf(item.getClassDate()));
            l.add(String.valueOf(item.getClassClassroom()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/class/count",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String count(){
        Result result =new Result();
        int count = classService.count();
        result.setData(count);
        result.setMsg("删除成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);

    }
}
