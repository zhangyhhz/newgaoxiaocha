package com.gaoxiaocha.dto;

import com.gaoxiaocha.pojo.Comments;
import com.gaoxiaocha.pojo.Dynamics;
import lombok.Data;

import java.util.List;

/**
 * 包括一个动态和其所有的评论
 *
 * @author zyh
 * @date 2020/5/26
 */
@Data
public class DynamicWithComments {
    private Dynamics       dynamics;
    private List<Comments> comments;
}
