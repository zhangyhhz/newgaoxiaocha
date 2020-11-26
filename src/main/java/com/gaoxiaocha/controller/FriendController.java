package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.pojo.Classes;
import com.gaoxiaocha.pojo.Dynamics;
import com.gaoxiaocha.pojo.Friend;
import com.gaoxiaocha.pojo.User;
import com.gaoxiaocha.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
     * 添加好友
     *
     * @author zyh
     * @date 2020/6/9
     */
    @RequestMapping(value = "/friend/addFriend", method = RequestMethod.POST)
    @ResponseBody
    public String addFriend(@RequestParam("userId") String userId,
                            @RequestParam("friendId") String friendId) {
        Result result = new Result();
        if (userId == null || friendId == null) {
            result.setMsg("添加失败");
            return JSONObject.toJSONString(result);
        }

        Boolean isSuccess = friendService.addFriend(userId, friendId);

        if (isSuccess == true) {
            result.setSuccess(true);
            result.setMsg("添加成功");
        } else {
            result.setMsg("添加失败");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除好友
     *
     * @author zyh
     * @date 2020/6/9
     */
    @RequestMapping(value = "/friend/deleteFriend", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("userId") String userId,
                         @RequestParam("friendId") String friendId) {
        Result result = new Result();
        Boolean isSuccess = friendService.delete(userId, friendId);
        if (isSuccess == true) {
            result.setSuccess(true);
            result.setMsg("删除成功");
        } else {
            result.setMsg("删除失败");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 查询所有好友
     *
     * @author zyh
     * @date 2020/6/9
     */
    @RequestMapping(value = "/friend/friendList", method = RequestMethod.GET)
    @ResponseBody
    public String listAll(@RequestParam("userId") String userId) {
        Result result = new Result();
        List<User> users = friendService.listAll(userId);
        result.setSuccess(true);
        result.setMsg("查询成功");
        result.setData(users);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/friend/charu", method = RequestMethod.POST)
    @ResponseBody
    public String charu(@RequestBody Friend friend) {
        Result result = new Result();
        if (friendService.insert(friend) == 1) {
            result.setMsg("插入成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("插入失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/friend/chazhao", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String chazhao() {
        Result<List<List<String>>> result = new Result();
        List<Friend> list = friendService.select();
        List<List<String>> lists = new ArrayList<>();
        for (Friend item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getId()));
            l.add(String.valueOf(item.getUserId()));
            l.add(String.valueOf(item.getFriendId()));
            l.add(String.valueOf(item.getGmtCreate()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/friend/gengxin", method = RequestMethod.POST)
    @ResponseBody
    public String gengxin(@RequestBody Friend friend) {
        Result result = new Result();
        if (friendService.update(friend) == 1) {
            result.setMsg("更新成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("更新失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/friend/shanchu", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String shanchu(@RequestBody Friend friend){
        Result result =new Result();
        if (friendService.delete(friend) == 1) {
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("删除失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }
    }

    @RequestMapping(value = "/friend/fenyechazhao",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String fenyechazhao(@RequestParam("currentPage") int currentPage,@RequestParam("numPerPage") int numperPage){
        Result<List<List<String>>> result =new Result();
        List<Friend> list = friendService.queryForPage(currentPage,numperPage);
        List<List<String>> lists = new ArrayList<>();
        for (Friend item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getId()));
            l.add(String.valueOf(item.getUserId()));
            l.add(String.valueOf(item.getFriendId()));
            l.add(String.valueOf(item.getGmtCreate()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/friend/count",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String count(){
        Result result =new Result();
        int count = friendService.count();
        result.setData(count);
        result.setMsg("删除成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);

    }
}
