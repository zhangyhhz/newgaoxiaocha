package com.gaoxiaocha.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Friend实体类
 *
 * @author zyh
 * @date 2020/6/9
 */
@TableName("friend")
@Data
public class Friend {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    String userId;

    String friendId;

    Long gmtCreate;

}
