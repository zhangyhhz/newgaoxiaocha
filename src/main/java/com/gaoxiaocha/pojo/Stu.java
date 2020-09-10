package com.gaoxiaocha.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Stu实体类
 *
 * @author zyh
 * @date 2020/5/6
 */
@Data
@TableName("stu")
public class Stu {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private String stuNo;

    private String xm;

    private String bj;

    private String zym;

    private String yxm;
}
