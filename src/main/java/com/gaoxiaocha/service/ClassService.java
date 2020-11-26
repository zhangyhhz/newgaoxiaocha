package com.gaoxiaocha.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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
    public int insert(Classes classes){
        return classMapper.insert(classes);
    }
    public List<Classes> select(){
        return classMapper.selectList(null);
    }
    public int update(Classes classes){
        return classMapper.updateById(classes);
    }
    public int delete(Classes classes){
        return classMapper.deleteById(classes.getId());
    }
    public List<Classes> queryForPage(int currentPage,int numPerPage){
        IPage<Classes> classesIPage = new Page<>(currentPage,numPerPage);
        classesIPage = classMapper.selectPage(classesIPage,null);
        List<Classes> list = classesIPage.getRecords();
        return  list;
    }

    public int count(){
        QueryWrapper<Classes> queryWrapper=new QueryWrapper();
        return classMapper.selectCount(queryWrapper);
    }

}
