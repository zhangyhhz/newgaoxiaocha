package com.gaoxiaocha.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fasterxml.jackson.databind.ser.Serializers;
import com.gaoxiaocha.pojo.Goods;
import org.apache.ibatis.annotations.*;

public interface GoodsMapper extends BaseMapper<GoodsMapper> {
    @Insert("insert into " +
            " goods(gname,gprice,gdescription,gstock,gtype,gparameter,gnorms,gintroduction,gpicture) " +
            " VALUES(#{gname},#{gprice},#{gdescription},#{gstock},#{gtype},#{gparameter},#{gnorms},#{gintroduction},#{gpicture}) ")
    boolean insert(Goods goods);

    @Delete("delete FROM goods WHERE gid=#{gid}")
    boolean delete(Goods goods);

    @Update("UPDATE goods SET gprice=#{gprice} where gid=#{gid}")
    boolean updateGpriceById(Goods goods);
    @Update("UPDATE goods SET gnorms=#{gnorms}-n where gid=#{gid}")
    boolean updateGnormsById(Goods goods);

    @Select("SELECT * FROM goods where gid=#{gid}")
    Goods select(Goods goods);
}

