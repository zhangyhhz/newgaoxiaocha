package com.gaoxiaocha.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaoxiaocha.mapper.UserMapper;
import com.gaoxiaocha.pojo.Classes;
import com.gaoxiaocha.pojo.Friend;
import com.gaoxiaocha.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * UserService
 *
 * @author zyh
 * @date 2020/5/6
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

    public boolean signUp(User user) {
        User one = userMapper.selectOne(new QueryWrapper<User>().eq("user_account", user.getUserAccount()));
        if (one == null) {
            int i = userMapper.insert(user);
            if (i == 1) {
                return true;
            }
        }
        return false;
    }

    public User login(String account, String pwd) {
        User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_account", account));
        if (user.getUserPwd().equals(pwd)) {
            return user;
        } else {
            return null;
        }
    }

    public List<User> searchByAccount(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_name",userName);
        List<User> users = userMapper.selectList(queryWrapper);
        return users;
    }

    public User searchByStuNo(String stuNo) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_stu_no", stuNo);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    public int insert(User user){
        System.out.println(user);
        return userMapper.insert(user);
    }
    public List<User> select(){
        return userMapper.selectList(null);
    }
    public int update(User user){
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_id",user.getUserId());
        return userMapper.update(user,updateWrapper);
    }
    public int delete(User user){
        return userMapper.deleteById(user.getUserId());
    }

    public List<User> queryForPage(int currentPage, int numPerPage){
        IPage<User> userIPage = new Page<>(currentPage,numPerPage);
        userIPage = userMapper.selectPage(userIPage,null);
        List<User> list = userIPage.getRecords();
        return  list;
    }

    public int count(){
        QueryWrapper<User> queryWrapper=new QueryWrapper();
        return userMapper.selectCount(queryWrapper);
    }
}
