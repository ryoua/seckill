package com.ryoua.seckill.mapper;

import com.ryoua.seckill.domain.SeckillGoods;
import com.ryoua.seckill.vo.GoodsVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @Author: ryoua
 * @Create: 2019-07-28 12:55
 */
@Mapper
public interface GoodsMapper {

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date, mg.seckill_price from seckill_goods mg left join goods g on mg.goods_id = g.id")
    List<GoodsVo> getAllGoodsVo();

    @Select("select g.*,mg.stock_count, mg.start_date, mg.end_date, mg.seckill_price from seckill_goods mg left join goods g on mg.goods_id = g.id where g.id = #{goodsId}")
    GoodsVo getGoodsVoByGoodsId(@Param("goodsId") Long goodsId);

    @Update("update seckill_goods set stock_count = stock_count - 1 where goods_id = #{goodsId}")
    int reduceStock(SeckillGoods goods);
}
