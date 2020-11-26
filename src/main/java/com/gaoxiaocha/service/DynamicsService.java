package com.gaoxiaocha.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaoxiaocha.dto.DynamicWithComments;
import com.gaoxiaocha.mapper.CommentsMapper;
import com.gaoxiaocha.mapper.DynamicsMapper;
import com.gaoxiaocha.pojo.Classes;
import com.gaoxiaocha.pojo.Comments;
import com.gaoxiaocha.pojo.Dynamics;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * DynamicsService
 *
 * @author zyh
 * @date 2020/5/23
 */
@Service
public class DynamicsService {

    @Resource
    private DynamicsMapper dynamicsMapper;

    @Resource
    private CommentsMapper commentsMapper;

    public int publish(int userId, String userName, String content, String img){
        Dynamics dynamics = new Dynamics();
        dynamics.setUserId(userId);
        dynamics.setUserName(userName);
        dynamics.setContent(content);
        dynamics.setImg(img);
        dynamics.setGmtCreate(System.currentTimeMillis());
        dynamics.setCommentCount(0);
        dynamics.setLikeCount(0);
        int insert = dynamicsMapper.insert(dynamics);
        if (insert==1){
            return dynamics.getId();
        }else {
            return -1;
        }
    }

    public List<DynamicWithComments> getDynamics(Integer indexPage) {
        QueryWrapper<Dynamics> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("gmt_create");

        Page<Dynamics> page = new Page<>(indexPage,10);
        Page<Dynamics> dynamicsPage = dynamicsMapper.selectPage(page, queryWrapper);
        List<Dynamics> records = dynamicsPage.getRecords();
        if (records==null||records.size()==0){
            return null;
        }
        List<DynamicWithComments> dynamicsWithComments = getDynamicsWithComments(records);
        return dynamicsWithComments;
    }

    public List<DynamicWithComments> getDynamicsById(Integer userId) {
        QueryWrapper<Dynamics> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        queryWrapper.orderByDesc("gmt_create");
        List<Dynamics> dynamics = dynamicsMapper.selectList(queryWrapper);
        if (dynamics==null||dynamics.size()==0){
            return null;
        }
        List<DynamicWithComments> dynamicsWithComments = getDynamicsWithComments(dynamics);
        return dynamicsWithComments;
    }

    public boolean like(Integer dynamicsID) {
        int i = dynamicsMapper.updateLikeCount(dynamicsID);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }

    public boolean unlike(Integer dynamicsID) {
        int i = dynamicsMapper.updateLikeCount2(dynamicsID);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }

    public boolean delete(Integer dynamicsID) {
        int i = dynamicsMapper.deleteById(dynamicsID);
        if (i==1){
            return true;
        }else {
            return false;
        }
    }

    /**
     * 组合动态和评论
     * @param dynamics
     * @return
     */
    public List<DynamicWithComments> getDynamicsWithComments(List<Dynamics> dynamics){
        ArrayList<DynamicWithComments> list = new ArrayList<>();
        for (Dynamics dynamic : dynamics) {
            DynamicWithComments dynamicWithComments = new DynamicWithComments();
            dynamicWithComments.setDynamics(dynamic);
            List<Comments> comments = getComments(dynamic.getId());
            dynamicWithComments.setComments(comments);
            list.add(dynamicWithComments);
        }
        return list;
    }


    /**
     * 获取动态的评论
     * @param dynamicsId
     * @return
     */
    public List<Comments> getComments(Integer dynamicsId){
        QueryWrapper<Comments> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("dynamics_id", dynamicsId);
        List<Comments> comments = commentsMapper.selectList(queryWrapper);
        return comments;
    }

    public DynamicWithComments getSingleDynamic(Integer dynamicsID) {
        Dynamics dynamics = dynamicsMapper.selectById(dynamicsID);
        List<Comments> comments = getComments(dynamicsID);
        DynamicWithComments dynamicWithComments = new DynamicWithComments();
        dynamicWithComments.setDynamics(dynamics);
        dynamicWithComments.setComments(comments);
        return dynamicWithComments;
    }

    public int insert(Dynamics dynamics){
        return dynamicsMapper.insert(dynamics);
    }
    public List<Dynamics> select(){
        return dynamicsMapper.selectList(null);
    }
    public int update(Dynamics dynamics){
        return dynamicsMapper.updateById(dynamics);
    }
    public int delete(Dynamics dynamics){
        return dynamicsMapper.deleteById(dynamics.getId());
    }

    public List<Dynamics> queryForPage(int currentPage, int numPerPage){
        IPage<Dynamics> dynamisIPage = new Page<>(currentPage,numPerPage);
        dynamisIPage = dynamicsMapper.selectPage(dynamisIPage,null);
        List<Dynamics> list = dynamisIPage.getRecords();
        return  list;
    }

    public int count(){
        QueryWrapper<Dynamics> queryWrapper=new QueryWrapper();
        return dynamicsMapper.selectCount(queryWrapper);
    }
}
