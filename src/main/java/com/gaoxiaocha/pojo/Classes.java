package com.gaoxiaocha.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Class实体类
 *
 * @author zyh
 * @date 2020/5/6
 */
@Data
@TableName("class")
public class Classes {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    private Integer stuNo;

    private String classType;

    private String className;

    private String classCode;

    private String classCategory;

    private String classStatus;

    private String classTeacher;

    private String classDate;

    private String classClassroom;
}
