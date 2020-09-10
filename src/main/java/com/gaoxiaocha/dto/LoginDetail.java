package com.gaoxiaocha.dto;

import com.gaoxiaocha.pojo.ImToken;
import com.gaoxiaocha.pojo.Stu;
import com.gaoxiaocha.pojo.User;
import lombok.Data;

/**
 * 登陆信息，包括用户信息和学生信息
 *
 * @author zyh
 * @date 2020/5/27
 */
@Data
public class LoginDetail {
    private User user;
    private Stu  stu;
    private ImToken token;
}
