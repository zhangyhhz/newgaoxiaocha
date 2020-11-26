package com.gaoxiaocha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author:xiongwei
 * @Date:2020/11/16-22:02
 * @Description:com.gaoxiaocha.mapper
 * @version:1.0
 */
@Repository
public interface DataBaseMapper{
    @Select("show tables")
    List<String> selectTables();
}
