package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.LoginDetail;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.pojo.Stu;
import com.gaoxiaocha.pojo.User;
import com.gaoxiaocha.service.StuService;
import com.gaoxiaocha.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * UserController
 *
 * @author zyh
 * @date 2020/5/6
 */
@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private StuService stuService;

    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    @ResponseBody
    public String signup(@RequestParam("id") String account,
                         @RequestParam("password") String password,
                         @RequestParam("stuNo") Integer stuNo,
                         @RequestParam("userName") String userName,
                         @RequestParam("userAvl") String userAvl) {

        Result result = new Result();

        if (account == null || account == "") {
            result.setMsg("用户名不能为空");
            return JSONObject.toJSONString(result);
        }
        if (password == null || password == "") {
            result.setMsg("密码不能为空");
            return JSONObject.toJSONString(result);
        }
        if (stuNo == null) {
            result.setMsg("学号不能为空");
            return JSONObject.toJSONString(result);
        }

        User user = new User();
        user.setUserAccount(account);
        user.setUserPwd(password);
        user.setUserStuNo(stuNo);
        user.setUserAvl(userAvl);
        user.setUserName(userName);
        Boolean isSuccess = userService.signUp(user);
        if (isSuccess) {
            Stu stu = stuService.stuInfo(stuNo);
            LoginDetail loginDetail = new LoginDetail();
            loginDetail.setStu(stu);
            loginDetail.setUser(user);
            result.setData(loginDetail);
            result.setMsg("SUCCESS");
            result.setSuccess(true);
        } else {
            result.setMsg("FALSE");
        }
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/user/login", method = RequestMethod.POST)
    @ResponseBody
    public String login(@RequestParam("id") String account,
                        @RequestParam("password") String password) {
//        String id = String.valueOf(jsonParam.get("id"));
//        String password = String.valueOf(jsonParam.get("password"));
        Result result = new Result();
        if (account == null || account == "") {
            result.setMsg("用户名不能为空");
            return JSONObject.toJSONString(result);
        }
        if (password == null || password == "") {
            result.setMsg("密码不能为空");
            return JSONObject.toJSONString(result);
        }
        User user = userService.login(account, password);
        if (user == null) {
            result.setMsg("登陆失败，用户名或密码错误");
        } else {
            Stu stu = stuService.getInfo(user.getUserStuNo());
            LoginDetail loginDetail = new LoginDetail();
            loginDetail.setStu(stu);
            loginDetail.setUser(user);
            result.setMsg("SUCCESS");
            result.setSuccess(true);
            result.setData(loginDetail);
        }
        return JSONObject.toJSONString(result);
    }
}
