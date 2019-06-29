package com.ryoua.seckill.dao;

import com.ryoua.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

/**
 * @Author ryoua Created on 2019-06-27
 */
public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(long seckillId, Date killTime);

    /**
     * id查询秒杀
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 查询秒杀列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAll(int offset, int limit);
}
