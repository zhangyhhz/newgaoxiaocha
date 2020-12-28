package com.gaoxiaocha.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@TableName("goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Integer gid;
    private  String gname;
    private Double gprice;
    private String gdescription;
    private String gpicture;
    private Integer gstuNo;
    private Date gtime;
}
