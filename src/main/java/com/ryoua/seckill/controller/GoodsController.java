package com.ryoua.seckill.controller;

import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.redis.RedisService;
import com.ryoua.seckill.service.GoodsService;
import com.ryoua.seckill.service.UserService;
import com.ryoua.seckill.vo.GoodsVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


/**
 * @Author: ryoua
 * @Create: 2019-07-27 16:35
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    UserService userService;

    @Autowired
    GoodsService goodsService;

    @Autowired
    RedisService redisService;

    @RequestMapping("/to_list")
    public String list(Model model,User user) {
        model.addAttribute("user", user);
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    @RequestMapping("/to_detail/{goodsId}")
    public String detail(Model model, User user, @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user", user);
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);
        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int status;
        int remainSeconds;
        if (now < startTime) {
            status = 0;
            remainSeconds = (int) ((startTime - now) / 1000);
        } else if(now > endTime) {
            status = 2;
            remainSeconds = -1;
        } else {
            status = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", status);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }


}
