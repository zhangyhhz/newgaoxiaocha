package com.gaoxiaocha;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.conditions.query.QueryChainWrapper;
import com.gaoxiaocha.mapper.ClassMapper;
import com.gaoxiaocha.mapper.UserMapper;
import com.gaoxiaocha.pojo.Classes;
import com.gaoxiaocha.pojo.User;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.Wrapper;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
class GaoxiaochaApplicationTests {

    @Resource
    private ClassMapper classMapper;

    @Test
    public void selectByStuNo() {
        List<Classes> classes = classMapper.selectList(new QueryWrapper<Classes>().eq("stu_no",2017210630));
        for (Classes aClass : classes) {
            System.out.println(aClass);
        }
    }

    @Resource
    private UserMapper userMapper;

    @Test
    public void signUp() {
        User user = new User();
        user.setUserAccount("123456");
        user.setUserPwd("123456789");
        user.setUserName("555");
        User one = userMapper.selectOne(new QueryWrapper<User>().eq("user_account", user.getUserAccount()));
        if (one == null) {
            int i = userMapper.insert(user);
            if (i == 1) {
                System.out.println(true);
            }
        }
        System.out.println(false);
    }
}
