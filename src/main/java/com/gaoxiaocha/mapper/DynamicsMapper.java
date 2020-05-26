package com.gaoxiaocha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gaoxiaocha.pojo.Dynamics;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * DynamicsMapper
 *
 * @author zyh
 * @date 2020/5/23
 */
public interface DynamicsMapper extends BaseMapper<Dynamics> {

    /**
     * 点赞数+1
     * @param id
     * @return
     */
    @Update("UPDATE dynamics SET like_count = like_count + 1 WHERE id = #{dynamicsID}")
    int updateLikeCount(@Param("dynamicsID")Integer id);

    /**
     * 点赞数-1
     * @param id
     * @return
     */
    @Update("UPDATE dynamics SET like_count = like_count -1 WHERE id = #{dynamicsID}")
    int updateLikeCount2(@Param("dynamicsID")Integer id);

    /**
     * 评论数+1
     * @param id
     * @return
     */
    @Update("UPDATE dynamics SET comment_count = comment_count + 1 WHERE id = #{dynamicsID}")
    int updateCommentCount(@Param("dynamicsID")Integer id);

    /**
     * 评论数-1
     * @param id
     * @return
     */
    @Update("UPDATE dynamics SET comment_count = comment_count -1 WHERE id = #{dynamicsID}")
    int updateCommentCount2(@Param("dynamicsID")Integer id);
}
