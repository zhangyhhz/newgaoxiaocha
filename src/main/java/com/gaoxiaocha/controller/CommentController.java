package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.mapper.DynamicsMapper;
import com.gaoxiaocha.pojo.Dynamics;
import com.gaoxiaocha.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;

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

}
