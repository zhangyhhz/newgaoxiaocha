package com.gaoxiaocha.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("goods")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private Integer gid;
    private  String gname;
    private Double gprice;
    private String gdescription;
    private Integer gstock;
    private String gtype;
    private String gparameter;
    private String gnorms;
    private String gintroduction;
    private String gpicture;
}
