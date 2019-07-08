package com.ryoua.seckill.mapper;

import com.ryoua.seckill.entity.SeckillGoods;
import com.ryoua.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author ryoua Created on 2019-07-07
 */
@Mapper
public interface GoodsMapper {

    @Select(value = "select g.*, sg.stock_count, sg.start_date, sg.end_date, sg.seckill_price  from sk_goods_seckill sg left join sk_goods g on sg.goods_id = g.id")
    public List<GoodsVo> listGoodsVo();

    @Select(value = "select g.*, sg.stock_count, sg.start_date, sg.end_date, sg.seckill_price  from sk_goods_seckill sg left join sk_goods g  on sg.goods_id = g.id where g.id = #{goodsId}")
    public GoodsVo getGoodsVoByGoodsId(@Param("goodsId") long goodsId);

    @Update(value = "update sk_goods_seckill set stock_count = stock_count - 1 where goods_id = #{goodsId}")
    public int reduceStock(SeckillGoods seckillGoods);
}
