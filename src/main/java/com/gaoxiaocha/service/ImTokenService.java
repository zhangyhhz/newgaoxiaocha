package com.gaoxiaocha.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaoxiaocha.mapper.ImTokenMapper;
import com.gaoxiaocha.pojo.Classes;
import com.gaoxiaocha.pojo.Friend;
import com.gaoxiaocha.pojo.ImToken;
import com.gaoxiaocha.pojo.Stu;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @Author:xiongwei
 * @Date:2020/11/16-21:35
 * @Description:com.gaoxiaocha.service
 * @version:1.0
 */

@Service
public class ImTokenService {
    @Resource
    private ImTokenMapper imTokenMapper;
    
    public int insert(ImToken imToken){
        return imTokenMapper.insert(imToken);
    }
    public List<ImToken> select(){
        return imTokenMapper.selectList(null);
    }
    public int update(ImToken imToken){
        UpdateWrapper<ImToken> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_account",imToken.getUserAccount());
        return imTokenMapper.update(imToken,updateWrapper);
    }
    public int delete(ImToken imToken){
        UpdateWrapper<ImToken> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("user_account",imToken.getUserAccount());
        return imTokenMapper.delete(updateWrapper);
    }

    public List<ImToken> queryForPage(int currentPage, int numPerPage){
        IPage<ImToken> imTokenIPage = new Page<>(currentPage,numPerPage);
        imTokenIPage = imTokenMapper.selectPage(imTokenIPage,null);
        List<ImToken> list = imTokenIPage.getRecords();
        return  list;
    }

    public int count(){
        QueryWrapper<ImToken> queryWrapper=new QueryWrapper();
        return imTokenMapper.selectCount(queryWrapper);
    }
}
