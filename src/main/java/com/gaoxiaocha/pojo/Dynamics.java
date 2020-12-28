package com.gaoxiaocha.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Dynamics实体类
 *
 * @author zyh
 * @date 2020/5/23
 */
@TableName("dynamics")
@Data
public class Dynamics {
    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    Integer userId;

    String userName;

    String content;

    String img;

    Long gmtCreate;

    Integer commentCount;

    Integer likeCount;

    String location;
}
