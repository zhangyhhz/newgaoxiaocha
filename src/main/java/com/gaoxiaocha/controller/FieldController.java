package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.mapper.FieldMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:xiongwei
 * @Date:2020/11/18-14:42
 * @Description:com.gaoxiaocha.controller
 * @version:1.0
 */
@RestController
public class FieldController {
    @Autowired
    FieldMapper fieldMapper;
    @GetMapping(value = "/field/select")
    public String selectField(@RequestParam("tableName") String tableName){
        List<String> field = fieldMapper.selectField(tableName);
        Result<List<String>> result = new Result<>();
        result.setMsg("查询成功");
        result.setSuccess(true);
        result.setData(field);
        return JSONObject.toJSONString(result);
    }

}
