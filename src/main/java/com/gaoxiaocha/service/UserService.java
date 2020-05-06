package com.gaoxiaocha.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gaoxiaocha.mapper.UserMapper;
import com.gaoxiaocha.pojo.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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
}
