package com.gaoxiaocha.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("im_token")
public class ImToken {
    private String userAccount;
    private String token;
}
