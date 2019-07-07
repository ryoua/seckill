package com.ryoua.seckill.service;

import com.ryoua.seckill.vo.GoodsVo;

import java.util.List;

/**
 * @Author ryoua Created on 2019-07-07
 */
public interface GoodsService {
    public List<GoodsVo> listGoodsVo();

    public GoodsVo getGoodsVoByGoodsId(long goodsId);
}
