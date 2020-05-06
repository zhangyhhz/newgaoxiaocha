package com.gaoxiaocha.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.gaoxiaocha.mapper.ClassMapper;
import com.gaoxiaocha.pojo.Classes;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * ClassService
 *
 * @author zyh
 * @date 2020/5/6
 */
@Service
public class ClassService {

    @Resource
    private ClassMapper classMapper;

    public List<Classes> selectByStuNo(Integer stuNo) {
        List<Classes> classes = classMapper.selectList(new QueryWrapper<Classes>().eq("stu_no", stuNo));
        return classes;
    }
}
