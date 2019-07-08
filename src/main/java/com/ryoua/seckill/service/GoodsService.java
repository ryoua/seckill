package com.ryoua.seckill.service;

import com.ryoua.seckill.vo.GoodsVo;

import java.util.List;

/**
 * @Author ryoua Created on 2019-07-07
 */
public interface GoodsService {
    /**
     * 查询商品列表
     * @return
     */
    public List<GoodsVo> listGoodsVo();

    /**
     * 通过商品Id得到商品详情
     * @param goodsId
     * @return
     */
    public GoodsVo getGoodsVoByGoodsId(long goodsId);

    /**
     * 减少库存，每次减一
     * @return
     */
    public void reduceStock(GoodsVo goods);
}
