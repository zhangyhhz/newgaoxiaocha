package com.gaoxiaocha.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 评论表
 *
 * @author zyh
 * @date 2020/5/26
 */
@Data
@TableName("comments")
public class Comments {

    @TableId(value = "id", type = IdType.AUTO)
    Integer id;

    Integer dynamicsId;

    Integer commentId;

    String userName;

    String comment;

    Long gmtCreate;
}
