package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.DynamicWithComments;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.pojo.Dynamics;
import com.gaoxiaocha.service.DynamicsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;

/**
 * DynamicsController
 *
 * @author zyh
 * @date 2020/5/23
 */
@Controller
public class DynamicsController {

    @Autowired
    private DynamicsService dynamicsService;


    /**
     * 发表动态
     * @param userId
     * @param content
     * @param img
     * @return
     */
    @RequestMapping(value = "/dynamics/publish", method = RequestMethod.POST)
    @ResponseBody
    public String publish(@RequestParam("id")Integer userId,
                          @RequestParam("name")String userName,
                          @RequestParam("content")String content,
                          @RequestParam("img")String img){
        Result result = new Result();
        if (userId==null){
            result.setMsg("用户未登录");
            return JSONObject.toJSONString(result);
        }
        if (content==null||content==""){
            result.setMsg("内容为空，发布失败");
            return JSONObject.toJSONString(result);
        }

        int dynamicsID = dynamicsService.publish(userId, userName, content, img);
        if (dynamicsID==-1){
            result.setMsg("发布失败!");
        }else {
            result.setSuccess(true);
            result.setMsg("发布成功!");
            HashMap<String, Integer> map = new HashMap<>();
            map.put("dynamicsID", dynamicsID);
            result.setData(map);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 分页查询动态
     * @param page
     * @return
     */
    @RequestMapping(value = "/dynamics/index",method = RequestMethod.GET)
    @ResponseBody
    public String getDynamics(@RequestParam("page")Integer page){
        Result result = new Result();
        List<DynamicWithComments> dynamics = dynamicsService.getDynamics(page);
        if (dynamics!=null){
            result.setData(dynamics);
            result.setSuccess(true);
        }else {
            result.setMsg("没有下一页了");
        }
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/dynamics",method = RequestMethod.GET)
    @ResponseBody
    public String getSingleDynamic(@RequestParam("id")Integer dynamicsID){
        Result result = new Result();
        DynamicWithComments singleDynamic = dynamicsService.getSingleDynamic(dynamicsID);
        if (singleDynamic.getDynamics()!=null){
            result.setSuccess(true);
            result.setData(singleDynamic);
        }else {
            result.setMsg("未找到该动态");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 查询用户动态
     * @param userId
     * @return
     */
    @RequestMapping(value = "/dynamics/myDynamics",method = RequestMethod.GET)
    @ResponseBody
    public String getDynamicsByID(@RequestParam("id")Integer userId){
        Result result = new Result();
        List<DynamicWithComments> dynamics = dynamicsService.getDynamicsById(userId);
        if (dynamics!=null){
            result.setData(dynamics);
            result.setSuccess(true);
        }else {
            result.setMsg("还没有发布过动态");
        }
        return JSONObject.toJSONString(result);
    }


    /**
     * 点赞+1
     * @param dynamicsID
     * @return
     */
    @RequestMapping(value = "/dynamics/like",method = RequestMethod.POST)
    @ResponseBody
    public String like(@RequestParam("id")Integer dynamicsID){
        Result result = new Result();
        boolean success = dynamicsService.like(dynamicsID);
        if (success==true){
            result.setSuccess(true);
            result.setMsg("点赞成功");
        }else {
            result.setMsg("点赞失败");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 点赞-1
     * @param dynamicsID
     * @return
     */
    @RequestMapping(value = "/dynamics/unlike",method = RequestMethod.POST)
    @ResponseBody
    public String unlike(@RequestParam("id")Integer dynamicsID){
        Result result = new Result();
        boolean success = dynamicsService.unlike(dynamicsID);
        if (success==true){
            result.setSuccess(true);
            result.setMsg("取消成功");
        }else {
            result.setMsg("取消失败");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除动态
     * @param dynamicsID
     * @return
     */
    @RequestMapping(value = "/dynamics/delete",method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("id")Integer dynamicsID){
        Result result = new Result();
        boolean success = dynamicsService.delete(dynamicsID);
        if (success==true){
            result.setSuccess(true);
            result.setMsg("删除成功");
        }else {
            result.setMsg("删除失败");
        }
        return JSONObject.toJSONString(result);
    }
}
