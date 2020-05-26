package com.gaoxiaocha.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gaoxiaocha.mapper.CommentsMapper;
import com.gaoxiaocha.mapper.DynamicsMapper;
import com.gaoxiaocha.pojo.Comments;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * CommentsService
 *
 * @author zyh
 * @date 2020/5/26
 */
@Service
public class CommentsService {

    @Resource
    private CommentsMapper commentsMapper;

    @Resource
    private DynamicsMapper dynamicsMapper;

    public int publish(Integer dynamicsId, Integer commentId, String userName, String content) {
        Comments comments = new Comments();
        comments.setDynamicsId(dynamicsId);
        if (commentId==null){
            comments.setCommentId(0);
        }else {
            comments.setCommentId(commentId);
        }
        comments.setUserName(userName);
        comments.setComment(content);
        comments.setGmtCreate(System.currentTimeMillis());
        int insert = commentsMapper.insert(comments);
        if (insert==1){
            dynamicsMapper.updateCommentCount(dynamicsId);
            return comments.getId();
        }else {
            return -1;
        }
    }

    public boolean delete(Integer dynamicsId, Integer commentId) {
        int i = commentsMapper.deleteById(commentId);
        if (i==1){
            dynamicsMapper.updateCommentCount2(dynamicsId);
            return true;
        }else {
            return false;
        }
    }
}
