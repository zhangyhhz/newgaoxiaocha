package com.gaoxiaocha.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author:xiongwei
 * @Date:2020/11/18-14:38
 * @Description:com.gaoxiaocha.mapper
 * @version:1.0
 */
@Component
public interface FieldMapper{

    @Select("select COLUMN_NAME from INFORMATION_SCHEMA.Columns where table_name=#{tableName} and TABLE_SCHEMA = 'gaoxiaocha'")
    List<String> selectField(String tableName);
}
