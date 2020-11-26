package com.gaoxiaocha.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.gaoxiaocha.pojo.Goods;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface GoodsMapper extends BaseMapper<Goods> {
    @Insert("insert into " +
            " goods(gname,gprice,gdescription,gstock,gpicture,guserid) " +
            " VALUES(#{gname},#{gprice},#{gdescription},#{gstock},#{gpicture},#{guserid}) ")
    int insert(Goods goods);

    @Delete("delete FROM goods WHERE gid=#{gid}")
    boolean delete(Goods goods);

    @Update("UPDATE goods SET gprice=#{gprice} where gid=#{gid}")
    boolean updateGpriceById(Goods goods);
    @Update("UPDATE goods SET gstock=gstock-1 where gid=#{gid}")
    boolean updateGstockById(Goods goods);
    @Update("UPDATE goods SET gname=#{gname},gprice=#{gprice},gdescription=#{gdescription},gstock=#{gstock},gpicture=#{gpicture},guserid=#{guserid} where gid=#{gid}")
    int updateById(Goods goods);

    @Select("SELECT * FROM goods where gid=#{gid}")
    Goods select(Goods goods);
    @Select("select * from goods")
    List<Goods> selectAll();
}
