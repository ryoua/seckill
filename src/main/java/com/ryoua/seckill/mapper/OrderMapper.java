package com.ryoua.seckill.mapper;

import com.ryoua.seckill.domain.OrderInfo;
import com.ryoua.seckill.domain.SeckillOrder;
import org.apache.ibatis.annotations.*;

/**
 * @Author: ryoua
 * @Create: 2019-07-28 13:43
 */
@Mapper
public interface OrderMapper {
    @Select("select * from seckill_order where user_id=#{userId} and goods_id=#{goodsId}")
    SeckillOrder getSeckillOrderByUserIdGoodsId(@Param("userId") Long userId, @Param("goodsId") long goodsId);

    @Insert("insert into order_info(user_id, goods_id, goods_name, goods_count, goods_price, order_channel, status, create_date)values("
            + "#{userId}, #{goodsId}, #{goodsName}, #{goodsCount}, #{goodsPrice}, #{orderChannel},#{status},#{createDate} )")
    @SelectKey(keyColumn="id", keyProperty="id", resultType=long.class, before=false, statement="select last_insert_id()")
    long insert(OrderInfo orderInfo);

    @Insert("insert into seckill_order (user_id, goods_id, order_id)values(#{userId}, #{goodsId}, #{orderId})")
     int insertSeckillOrder(SeckillOrder seckillOrder);
}
