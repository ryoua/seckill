package com.ryoua.seckill.controller;

import com.ryoua.seckill.domain.OrderInfo;
import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.result.CodeMsg;
import com.ryoua.seckill.result.Result;
import com.ryoua.seckill.vo.GoodsVo;
import com.ryoua.seckill.vo.OrderDetailVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: ryoua
 * @Create: 2019-08-03 10:07
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BasicController {

    @RequestMapping(value = "/detail")
    @ResponseBody
    public Result<OrderDetailVo> getOrderDetail(
            @RequestParam("orderId") Long orderId,
            User user) {
        if (user == null)
            return Result.error(CodeMsg.SESSION_ERROR);

        OrderInfo orderInfo = orderService.getOrderById(orderId);
        if (orderInfo == null)
            return Result.error(CodeMsg.ORDER_NOT_EXIST);

        Long goodsId = orderInfo.getGoodsId();
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);

        OrderDetailVo orderDetailVo = new OrderDetailVo();
        orderDetailVo.setGoods(goods);
        orderDetailVo.setOrder(orderInfo);
        return Result.success(orderDetailVo);
    }
}
