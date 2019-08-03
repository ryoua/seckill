package com.ryoua.seckill.controller;

import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.result.Result;
import com.ryoua.seckill.vo.OrderDetailVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Author: ryoua
 * @Create: 2019-08-03 10:07
 */
@Controller
@RequestMapping("/order")
public class OrderController extends BasicController {

    @RequestMapping(value = "/detail")
    public Result<OrderDetailVo> getOrderDetail(
            @RequestParam("orderId") Long orderId,
            User user) {

    }
}
