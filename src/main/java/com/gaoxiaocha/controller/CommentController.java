package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.mapper.DynamicsMapper;
import com.gaoxiaocha.pojo.Classes;
import com.gaoxiaocha.pojo.Comments;
import com.gaoxiaocha.pojo.Dynamics;
import com.gaoxiaocha.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * CommentController
 *
 * @author zyh
 * @date 2020/5/26
 */
@Controller
public class CommentController {

    @Autowired
    private CommentsService commentsService;

    /**
     * 评论动态或评论
     *
     * @param dynamicsId
     * @param userName
     * @param content
     * @return
     */
    @RequestMapping(value = "/comments/publish", method = RequestMethod.POST)
    @ResponseBody
    public String publish(@RequestParam("id") Integer dynamicsId,
                          @RequestParam(value = "commentId", required = false) Integer commentId,
                          @RequestParam("name") String userName,
                          @RequestParam("content") String content) {
        Result result = new Result();

        if (dynamicsId == null) {
            result.setMsg("未找到该动态");
            return JSONObject.toJSONString(result);
        }
        if (content == null || content == "") {
            result.setMsg("内容为空，发布失败");
            return JSONObject.toJSONString(result);
        }

        // 评论id
        int id;

        if (commentId == null) {
            id = commentsService.publish(dynamicsId, null, userName, content);
        } else {
            id = commentsService.publish(dynamicsId, commentId, userName, content);
        }

        if (id == -1) {
            result.setMsg("评论失败");
        } else {
            result.setSuccess(true);
            result.setMsg("评论成功");
            HashMap<String, Integer> map = new HashMap<>();
            map.put("commentId", id);
            result.setData(map);
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 删除评论
     * @param dynamicsId
     * @param commentId
     * @return
     */
    @RequestMapping(value = "/comments/delete", method = RequestMethod.POST)
    @ResponseBody
    public String delete(@RequestParam("id") Integer dynamicsId,
                         @RequestParam("id") Integer commentId) {
        Result result = new Result();
        boolean success = commentsService.delete(dynamicsId, commentId);
        if (success == true) {
            result.setSuccess(true);
            result.setMsg("删除成功");
        } else {
            result.setMsg("删除失败");
        }
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/comments/charu",method = RequestMethod.POST)
    @ResponseBody
    public String charu(@RequestBody Comments comments){
        Result result =new Result();
        if(commentsService.insert(comments)==1) {
            result.setMsg("插入成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        }else {
            result.setMsg("插入失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/comments/chazhao",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String chazhao(){
        Result<List<List<String>>> result =new Result();
        List<Comments> list = commentsService.select();

        List<List<String>> lists = new ArrayList<>();
        for (Comments item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getId()));
            l.add(String.valueOf(item.getDynamicsId()));
            l.add(String.valueOf(item.getCommentId()));
            l.add(String.valueOf(item.getUserName()));
            l.add(String.valueOf(item.getComment()));
            l.add(String.valueOf(item.getGmtCreate()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/comments/gengxin",method = RequestMethod.POST)
    @ResponseBody
    public String gengxin(@RequestBody Comments comments){
        Result result =new Result();
        if(commentsService.update(comments)==1) {
            result.setMsg("更新成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        }else {
            result.setMsg("更新失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/comments/shanchu",method = RequestMethod.POST)
    @ResponseBody
    public String shanchu(@RequestBody Comments comments){
        Result result =new Result();
        if(commentsService.delete(comments)==1) {
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        }else {
            result.setMsg("删除失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/comments/fenyechazhao",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String fenyechazhao(@RequestParam("currentPage") int currentPage,@RequestParam("numPerPage") int numperPage){
        Result<List<List<String>>> result =new Result();
        List<Comments> list = commentsService.queryForPage(currentPage,numperPage);
        List<List<String>> lists = new ArrayList<>();
        for (Comments item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getId()));
            l.add(String.valueOf(item.getDynamicsId()));
            l.add(String.valueOf(item.getCommentId()));
            l.add(String.valueOf(item.getUserName()));
            l.add(String.valueOf(item.getComment()));
            l.add(String.valueOf(item.getGmtCreate()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/comments/count",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String count(){
        Result result =new Result();
        int count = commentsService.count();
        result.setData(count);
        result.setMsg("删除成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);

    }
}
