package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.LoginDetail;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.pojo.*;
import com.gaoxiaocha.service.RongCloudService;
import com.gaoxiaocha.service.StuService;
import com.gaoxiaocha.service.UserService;
import com.gaoxiaocha.util.CheckStr;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * UserController
 *
 * @author zyh
 * @date 2020/5/6
 */
@Controller
public class UserController {

    private final UserService userService;

    private final StuService stuService;

    private final RongCloudService cloudService;

    public UserController(UserService userService, StuService stuService, RongCloudService cloudService) {
        this.userService = userService;
        this.stuService = stuService;
        this.cloudService = cloudService;
    }

    /**
     * 注册
     *
     * @param account
     * @param password
     * @param stuNo
     * @param userName
     * @param userAvl
     * @return
     */
    @RequestMapping(value = "/user/signup", method = RequestMethod.POST)
    @ResponseBody
    public String signUp(@RequestParam("id") String account,
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
            ImToken register = cloudService.register(account, userName, userAvl);
            LoginDetail loginDetail = new LoginDetail();
            loginDetail.setStu(stu);
            loginDetail.setUser(user);
            loginDetail.setToken(register);
            result.setData(loginDetail);
            result.setMsg("SUCCESS");
            result.setSuccess(true);
        } else {
            result.setMsg("FALSE");
        }
        return JSONObject.toJSONString(result);
    }

    /**
     * 登录
     *
     * @param account
     * @param password
     * @return
     */
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
            ImToken token = cloudService.getToken(account);
            LoginDetail loginDetail = new LoginDetail();
            loginDetail.setStu(stu);
            loginDetail.setUser(user);
            loginDetail.setToken(token);
            result.setMsg("SUCCESS");
            result.setSuccess(true);
            result.setData(loginDetail);
        }
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/user/search", method = RequestMethod.GET)
    @ResponseBody
    public String search(@RequestParam(required = false, value = "condition") String condition) {

        Result result = new Result();
        if (condition == null) {
            result.setMsg("输入条件为空!");
            return JSONObject.toJSONString(result);
        }

        boolean b = CheckStr.checkStr(condition);

        if (!b){
            // 用户名
            List<User> users = userService.searchByAccount(condition);
            if (users.size()>0){
                ArrayList<LoginDetail> loginDetailList = new ArrayList<>();
                for (User user : users) {
                    Stu info = stuService.getInfo(user.getUserStuNo());
                    LoginDetail loginDetail = new LoginDetail();
                    loginDetail.setUser(user);
                    loginDetail.setStu(info);
                    loginDetailList.add(loginDetail);
                }
                result.setSuccess(true);
                result.setData(loginDetailList);
            }else {
                result.setMsg("未找到用户");
            }
        }else {
            // 学号
            User user = userService.searchByStuNo(condition);
            if (user!=null){
                LoginDetail loginDetail = new LoginDetail();
                Stu info = stuService.getInfo(user.getUserStuNo());
                loginDetail.setStu(info);
                loginDetail.setUser(user);
                result.setSuccess(true);
                result.setData(loginDetail);
            }else {
                result.setMsg("未找到用户");
            }
        }
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/user/getImToken", method = RequestMethod.GET)
    @ResponseBody
    public String getImToken(@RequestParam("id") String account){
        Result result = new Result();
        if (account==null){
            result.setMsg("用户账号为空");
            return JSONObject.toJSONString(result);
        }

        ImToken token = cloudService.getToken(account);
        if (token!=null){
            result.setSuccess(true);
            result.setData(token);
            result.setMsg("请求成功");
        }else {
            result.setMsg("Token不存在");
        }
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/user/charu", method = RequestMethod.POST)
    @ResponseBody
    public String charu(@RequestBody User user) {
        Result result = new Result();
        if (userService.insert(user) == 1) {
            result.setMsg("插入成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("插入失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/user/chazhao", method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String chazhao() {
        List<User> list = userService.select();
        Result<List<List<String>>> result =new Result();
        List<List<String>> lists = new ArrayList<>();
        for (User item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getUserId()));
            l.add(String.valueOf(item.getUserAccount()));
            l.add(String.valueOf(item.getUserPwd()));
            l.add(String.valueOf(item.getUserName()));
            l.add(String.valueOf(item.getUserStuNo()));
            l.add(String.valueOf(item.getUserAvl()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/user/gengxin", method = RequestMethod.POST)
    @ResponseBody
    public String gengxin(@RequestBody User user) {
        Result result = new Result();
        if (userService.update(user) == 1) {
            result.setMsg("更新成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("更新失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }

    }

    @RequestMapping(value = "/user/shanchu", method = RequestMethod.POST)
    @ResponseBody
    public String shanchu(@RequestBody User user) {
        Result result = new Result();
        if (userService.delete(user) == 1) {
            result.setMsg("删除成功！");
            result.setSuccess(true);
            return JSONObject.toJSONString(result);
        } else {
            result.setMsg("删除失败");
            result.setSuccess(false);
            return JSONObject.toJSONString(result);
        }
    }

    @RequestMapping(value = "/user/fenyechazhao",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String fenyechazhao(@RequestParam("currentPage") int currentPage,@RequestParam("numPerPage") int numperPage){
        Result<List<List<String>>> result =new Result();
        List<User> list = userService.queryForPage(currentPage,numperPage);
        List<List<String>> lists = new ArrayList<>();
        for (User item:list){
            List<String> l = new ArrayList<>();
            l.add(String.valueOf(item.getUserId()));
            l.add(String.valueOf(item.getUserAccount()));
            l.add(String.valueOf(item.getUserPwd()));
            l.add(String.valueOf(item.getUserName()));
            l.add(String.valueOf(item.getUserStuNo()));
            l.add(String.valueOf(item.getUserAvl()));
            lists.add(l);
        }
        result.setData(lists);
        result.setMsg("查找成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);
    }

    @RequestMapping(value = "/user/count",method = {RequestMethod.POST,RequestMethod.GET})
    @ResponseBody
    public String count(){
        Result result =new Result();
        int count = userService.count();
        result.setData(count);
        result.setMsg("删除成功！");
        result.setSuccess(true);
        return JSONObject.toJSONString(result);

    }
}
