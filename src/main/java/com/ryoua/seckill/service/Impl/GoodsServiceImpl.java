package com.ryoua.seckill.service.Impl;

import com.ryoua.seckill.entity.SeckillGoods;
import com.ryoua.seckill.mapper.GoodsMapper;
import com.ryoua.seckill.service.GoodsService;
import com.ryoua.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author ryoua Created on 2019-07-07
 */
@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.listGoodsVo();
    }

    @Override
    public GoodsVo getGoodsVoByGoodsId(long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    @Override
    public void reduceStock(GoodsVo goods){
        SeckillGoods sg = new SeckillGoods();
        sg.setGoodsId(goods.getId());
        goodsMapper.reduceStock(sg);
    }
}
