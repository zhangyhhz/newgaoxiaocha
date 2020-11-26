package com.gaoxiaocha.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaoxiaocha.mapper.CommentsMapper;
import com.gaoxiaocha.mapper.DynamicsMapper;
import com.gaoxiaocha.pojo.Classes;
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

    public int insert(Comments comments){
        return commentsMapper.insert(comments);
    }
    public List<Comments> select(){
        return commentsMapper.selectList(null);
    }
    public int update(Comments comments){
        return commentsMapper.updateById(comments);
    }
    public int delete(Comments comments){
        return commentsMapper.deleteById(comments.getId());
    }

    public List<Comments> queryForPage(int currentPage,int numPerPage){
        IPage<Comments> commentsIPage = new Page<>(currentPage,numPerPage);
        commentsIPage = commentsMapper.selectPage(commentsIPage,null);
        List<Comments> list = commentsIPage.getRecords();
        return  list;
    }

    public int count(){
        QueryWrapper<Comments> queryWrapper=new QueryWrapper();
        return commentsMapper.selectCount(queryWrapper);
    }
}
