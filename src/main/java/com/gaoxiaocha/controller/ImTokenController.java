package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.pojo.Friend;
import com.gaoxiaocha.pojo.ImToken;
import com.gaoxiaocha.service.ImTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author:xiongwei
 * @Date:2020/11/16-21:45
 * @Description:com.gaoxiaocha.controller
 * @version:1.0
 */

@Controller
public class ImTokenController {
    @Autowired
    private ImTokenService imTokenService;

    @RequestMapping(value = "/im_token/charu", method = RequestMethod.POST)
    @ResponseBody
    public String charu(@RequestBody ImToken imToken) {
        Result result = new Result();
        if (imTokenService.insert(imToken) == 1) {
            result.setMsg("插入成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("插入失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/im_token/chazhao", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String chazhao() {
        Result<List<List<String>>> result = new Result();
        List<ImToken> list = imTokenService.select();
        List<List<String>> lists = new ArrayList<>();
        for (ImToken item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getUserAccount()));
            l.add(String.valueOf(item.getToken()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/im_token/gengxin", method = RequestMethod.POST)
    @ResponseBody
    public String gengxin(@RequestBody ImToken imToken) {
        Result result = new Result();
        if (imTokenService.update(imToken) == 1) {
            result.setMsg("更新成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("更新失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/im_token/shanchu", method = RequestMethod.POST)
    @ResponseBody
    public String shanchu(@RequestBody ImToken imToken) {
        Result result = new Result();
        if (imTokenService.delete(imToken) == 1) {
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("删除失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }
    }

    @RequestMapping(value = "/im_token/fenyechazhao",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String fenyechazhao(@RequestParam("currentPage") int currentPage, @RequestParam("numPerPage") int numperPage){
        Result<List<List<String>>> result =new Result();
        List<ImToken> list = imTokenService.queryForPage(currentPage,numperPage);
        List<List<String>> lists = new ArrayList<>();
        for (ImToken item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getUserAccount()));
            l.add(String.valueOf(item.getToken()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/im_token/count",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String count(){
        Result result =new Result();
        int count = imTokenService.count();
        result.setData(count);
        result.setMsg("删除成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);

    }
}
