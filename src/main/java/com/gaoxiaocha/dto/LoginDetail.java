package com.gaoxiaocha.dto;

import com.gaoxiaocha.pojo.Stu;
import com.gaoxiaocha.pojo.User;
import lombok.Data;

@Data
public class LoginDetail {
    private User user;
    private Stu  stu;
}
