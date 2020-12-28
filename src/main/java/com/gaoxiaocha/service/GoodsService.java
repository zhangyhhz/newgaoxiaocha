package com.gaoxiaocha.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gaoxiaocha.mapper.GoodsMapper;
import com.gaoxiaocha.pojo.Goods;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.*;
import java.util.*;

@Service
public class GoodsService {
    @Resource
    private GoodsMapper goodsMapper;

    public int charu(Goods goods){
        return goodsMapper.insert(goods);
    }
    public boolean shanchu(Goods goods){
        return goodsMapper.delete(goods);
    }
    public boolean xiugaijiage(Goods goods){
        return goodsMapper.updateGpriceById(goods);
    }
    public int xiugai(Goods goods)  {return goodsMapper.updateById(goods);}

    public Goods chazhao(Goods goods) {
        return goodsMapper.select(goods);
    }
    public List<Goods> chazhaosuoyou(){
        return goodsMapper.selectAll();
    }

    public List<Goods> queryForPage(int currentPage, int numPerPage){
        IPage<Goods> goodsIPage = new Page<>(currentPage,numPerPage);
        goodsIPage = goodsMapper.selectPage(goodsIPage,null);
        List<Goods> list = goodsIPage.getRecords();
        return  list;
    }

    public int count(){
        QueryWrapper<Goods> queryWrapper=new QueryWrapper();
        return goodsMapper.selectCount(queryWrapper);
    }
}

