package com.gaoxiaocha.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * User实体类
 *
 * @author zyh
 * @date 2020/5/6
 */
@Data
@TableName("user")
public class User {
    @TableId(value = "user_id", type = IdType.AUTO)
    private Integer userId;

    private String userAccount;

    private String userName;

    private String userPwd;

    private Integer userStuNo;

    private String userAvl;
}
