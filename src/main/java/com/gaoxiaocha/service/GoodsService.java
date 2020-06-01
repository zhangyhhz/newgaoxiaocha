package com.gaoxiaocha.service;

import com.gaoxiaocha.mapper.GoodsMapper;
import com.gaoxiaocha.pojo.Goods;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class GoodsService {
    @Resource
    private GoodsMapper goodsMapper;
    public boolean charu(Goods goods){
        return goodsMapper.insert(goods);
    }
    public boolean shanchu(Goods goods){
        return goodsMapper.delete(goods);
    }
    public boolean xiugaikucun(Goods goods){
        return goodsMapper.updateGnormsById(goods);
    }
    public boolean xiugaijiage(Goods goods){
        return goodsMapper.updateGpriceById(goods);
    }
    public Goods chazhao(Goods goods) {
        return goodsMapper.select(goods);
    }
}

