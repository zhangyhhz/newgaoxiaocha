package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.pojo.Friend;
import com.gaoxiaocha.pojo.User;
import com.gaoxiaocha.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 好友
 *
 * @author zyh
 * @date 2020/6/9
 */
@Controller
public class FriendController {

    @Autowired
    private FriendService friendService;

    /**
     *添加好友
     *
     *@author zyh
     *@date 2020/6/9
     */
    @RequestMapping(value = "/friend/addFriend", method = RequestMethod.POST)
    @ResponseBody
    public String addFriend(@RequestParam("userId")Integer userId,
                            @RequestParam("friendId")Integer friendId){
        Result result = new Result();
        if (userId==null||friendId==null){
            result.setMsg("添加失败");
            return JSONObject.toJSONString(result);
        }

        Boolean isSuccess = friendService.addFriend(userId, friendId);

        if (isSuccess==true){
            result.setSuccess(true);
            result.setMsg("添加成功");
        }else {
            result.setMsg("添加失败");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     *删除好友
     *
     *@author zyh
     *@date 2020/6/9
     */
    @RequestMapping(value = "/friend/deleteFriend", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("userId")Integer userId,
                            @RequestParam("friendId")Integer friendId){
        Result result = new Result();
        Boolean isSuccess = friendService.delete(userId, friendId);
        if (isSuccess==true){
            result.setSuccess(true);
            result.setMsg("删除成功");
        }else {
            result.setMsg("删除失败");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     *查询所有好友
     *
     *@author zyh
     *@date 2020/6/9
     */
    @RequestMapping(value = "/friend/friendList", method = RequestMethod.GET)
    @ResponseBody
    public String listAll(@RequestParam("userId")Integer userId){
        Result result = new Result();
        List<User> users = friendService.listAll(userId);
        result.setSuccess(true);
        result.setMsg("查询成功");
        result.setData(users);
        return JSONObject.toJSONString(result);
    }
}
