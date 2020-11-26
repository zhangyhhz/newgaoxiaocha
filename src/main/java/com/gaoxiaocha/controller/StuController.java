package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.pojo.Friend;
import com.gaoxiaocha.pojo.Stu;
import com.gaoxiaocha.pojo.User;
import com.gaoxiaocha.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:xiongwei
 * @Date:2020/11/16-21:28
 * @Description:com.gaoxiaocha.controller
 * @version:1.0
 */

@Controller
public class StuController {
    @Autowired
    private StuService stuService;

    @RequestMapping(value = "/stu/charu", method = RequestMethod.POST)
    @ResponseBody
    public String charu(@RequestBody Stu stu) {
        Result result = new Result();
        if (stuService.insert(stu) == 1) {
            result.setMsg("插入成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("插入失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/stu/chazhao", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String chazhao() {
        Result<List<List<String>>> result = new Result();
        List<Stu> list = stuService.select();
        List<List<String>> lists = new ArrayList<>();
        for (Stu item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getId()));
            l.add(String.valueOf(item.getStuNo()));
            l.add(String.valueOf(item.getXm()));
            l.add(String.valueOf(item.getBj()));
            l.add(String.valueOf(item.getZym()));
            l.add(String.valueOf(item.getYxm()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/stu/gengxin", method = RequestMethod.POST)
    @ResponseBody
    public String gengxin(@RequestBody Stu stu) {
        Result result = new Result();
        if (stuService.update(stu) == 1) {
            result.setMsg("更新成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("更新失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/stu/shanchu", method = RequestMethod.POST)
    @ResponseBody
    public String shanchu(@RequestBody Stu stu) {
        Result result = new Result();
        if (stuService.delete(stu) == 1) {
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("删除失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }
    }

    @RequestMapping(value = "/stu/fenyechazhao",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String fenyechazhao(@RequestParam("currentPage") int currentPage, @RequestParam("numPerPage") int numperPage){
        Result<List<List<String>>> result =new Result();
        List<Stu> list = stuService.queryForPage(currentPage,numperPage);
        List<List<String>> lists = new ArrayList<>();
        for (Stu item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getId()));
            l.add(String.valueOf(item.getStuNo()));
            l.add(String.valueOf(item.getXm()));
            l.add(String.valueOf(item.getBj()));
            l.add(String.valueOf(item.getZym()));
            l.add(String.valueOf(item.getYxm()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/stu/count",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String count(){
        Result result =new Result();
        int count = stuService.count();
        result.setData(count);
        result.setMsg("删除成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);

    }
}
