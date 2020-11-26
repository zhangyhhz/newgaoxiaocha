package com.gaoxiaocha.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaoxiaocha.mapper.FriendMapper;
import com.gaoxiaocha.mapper.UserMapper;
import com.gaoxiaocha.pojo.Classes;
import com.gaoxiaocha.pojo.Friend;
import com.gaoxiaocha.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class FriendService {

    @Resource
    private FriendMapper friendMapper;

    @Resource
    private UserMapper userMapper;

    public Boolean addFriend(String userId, String friendId) {
        Friend friend = new Friend();
        friend.setFriendId(friendId);
        friend.setUserId(userId);
        friend.setGmtCreate(System.currentTimeMillis());

        int insert = friendMapper.insert(friend);
        if (insert == 1) {
            return true;
        } else {
            return false;
        }
    }

    public Boolean delete(String userId, String friendId) {
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.eq("friend_id", friendId);
        int delete = friendMapper.delete(wrapper);
        if (delete == 1) {
            return true;
        } else {
            return false;
        }
    }

    public List<User> listAll(String userId) {
        QueryWrapper<Friend> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);

        List<Friend> friends = friendMapper.selectList(wrapper);
        List<User> users = new ArrayList<User>();

        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        for (Friend friend : friends) {
            queryWrapper.eq("user_id",Integer.parseInt(friend.getFriendId()));
            User user = userMapper.selectOne(queryWrapper);
            users.add(user);
        }
        return users;
    }

    public int insert(Friend friend){
        return friendMapper.insert(friend);
    }
    public List<Friend> select(){
        return friendMapper.selectList(null);
    }
    public int update(Friend friend){
        return friendMapper.updateById(friend);
    }
    public int delete(Friend friend){
        return friendMapper.deleteById(friend.getId());
    }

    public List<Friend> queryForPage(int currentPage, int numPerPage){
        IPage<Friend> friendIPage = new Page<>(currentPage,numPerPage);
        friendIPage = friendMapper.selectPage(friendIPage,null);
        List<Friend> list = friendIPage.getRecords();
        return  list;
    }

    public int count(){
        QueryWrapper<Friend> queryWrapper=new QueryWrapper();
        return friendMapper.selectCount(queryWrapper);
    }
}
