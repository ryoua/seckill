package com.ryoua.seckill.controller;

import com.ryoua.seckill.domain.OrderInfo;
import com.ryoua.seckill.domain.SeckillOrder;
import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.redis.RedisService;
import com.ryoua.seckill.result.CodeMsg;
import com.ryoua.seckill.result.Result;
import com.ryoua.seckill.service.GoodsService;
import com.ryoua.seckill.service.OrderService;
import com.ryoua.seckill.service.SeckillService;
import com.ryoua.seckill.service.UserService;
import com.ryoua.seckill.vo.GoodsVo;
import com.sun.org.apache.bcel.internal.classfile.Code;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: ryoua
 * @Create: 2019-07-28 13:39
 */
@Controller
@RequestMapping("/miaosha")
public class SeckillController extends BasicController {

    @RequestMapping(value = "/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public Result<OrderInfo> list(Model model, User user,
                       @RequestParam("goodsId")long goodsId) {
        model.addAttribute("user", user);
        if (user == null)
            return Result.error(CodeMsg.SESSION_ERROR);
        // 判断库存
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        int stock = goods.getStockCount();
        if (stock <= 0) {
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }
        // 判断是否秒杀到
        SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null) {
            return Result.error(CodeMsg.REPEATE_MIAOSHA);
        }
        // 秒杀
        OrderInfo orderInfo = seckillService.seckill(user, goods);
        return Result.success(orderInfo);
    }

}
