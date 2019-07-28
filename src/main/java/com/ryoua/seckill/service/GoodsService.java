package com.ryoua.seckill.service;

import com.ryoua.seckill.domain.SeckillGoods;
import com.ryoua.seckill.mapper.GoodsMapper;
import com.ryoua.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: ryoua
 * @Create: 2019-07-28 12:55
 */
@Service
public class GoodsService {

    @Autowired
    GoodsMapper goodsMapper;

    public List<GoodsVo> listGoodsVo() {
        return goodsMapper.getAllGoodsVo();
    }

    public GoodsVo getGoodsVoByGoodsId(Long goodsId) {
        return goodsMapper.getGoodsVoByGoodsId(goodsId);
    }

    public void reduceStock(GoodsVo goods) {
        SeckillGoods g = new SeckillGoods();
        g.setGoodsId(goods.getId());
        goodsMapper.reduceStock(g);
    }
}
