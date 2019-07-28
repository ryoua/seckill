package com.ryoua.seckill.controller;

import com.ryoua.seckill.domain.OrderInfo;
import com.ryoua.seckill.domain.SeckillOrder;
import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.redis.RedisService;
import com.ryoua.seckill.result.CodeMsg;
import com.ryoua.seckill.service.GoodsService;
import com.ryoua.seckill.service.OrderService;
import com.ryoua.seckill.service.SeckillService;
import com.ryoua.seckill.service.UserService;
import com.ryoua.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: ryoua
 * @Create: 2019-07-28 13:39
 */
@Controller
@RequestMapping("/miaosha")
public class SeckillController {

    @Autowired
    UserService userService;

    @Autowired
    RedisService redisService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    OrderService orderService;

    @Autowired
    SeckillService seckillService;

    @RequestMapping("/do_miaosha")
    public String list(Model model, User user,
                       @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if (user == null)
            return "login";
        // 判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getGoodsStock();
        if (stock <= 0) {
            model.addAttribute("errmsg", CodeMsg.MIAO_SHA_OVER.getMsg());
            return "miaosha_failed";
        }
        // 判断是否秒杀到
        SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            model.addAttribute("errmsg", CodeMsg.REPEATE_MIAOSHA.getMsg());
            return "miaosha_failed";
        }
        // 秒杀
        OrderInfo orderInfo = seckillService.seckill(user, goods);
        model.addAttribute("orderInfo", orderInfo);
        model.addAttribute("goods", goods);
        return "order_detail";
    }

}
