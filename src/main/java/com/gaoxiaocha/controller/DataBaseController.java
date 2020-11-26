package com.gaoxiaocha.controller;

import com.alibaba.fastjson.JSONObject;
import com.gaoxiaocha.dto.Result;
import com.gaoxiaocha.mapper.DataBaseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author:xiongwei
 * @Date:2020/11/16-22:06
 * @Description:com.gaoxiaocha.controller
 * @version:1.0
 */
@RestController
public class DataBaseController {
    @Autowired
    private DataBaseMapper dataBaseMapper;
    @RequestMapping(value = "/database/view",method = RequestMethod.GET)
    public String tables(){
        Result<List<String>> listResult = new Result<>();
        List<String> strings = dataBaseMapper.selectTables();
        listResult.setData(strings);
        listResult.setSuccess(true);
        listResult.setMsg("查表成功");
        return JSONObject.toJSONString(listResult);
    }
}
