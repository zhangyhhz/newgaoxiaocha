<<<<<<< HEAD
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
    private String gpicture;
    private Integer guserid;
}
=======
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
    private String gpicture;
}
>>>>>>> 14611d12e04a5af814edb4e5e2ef9142b825dc57
