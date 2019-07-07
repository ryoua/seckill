package com.ryoua.seckill.controller;

import com.ryoua.seckill.entity.User;
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
 * @Author ryoua Created on 2019-07-07
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private GoodsService goodsService;

    /**
     * 展示商品列表
     * @param model
     * @param user
     * @return
     */
    @RequestMapping("/to_list")
    public String list(Model model, User user) {
        model.addAttribute("user", user);
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        return "goods_list";
    }

    /**
     * 商品详情
     * @param model
     * @param user
     * @param goodsId
     * @return
     */
    @RequestMapping("/to_detail/{goodsId}")
    public String goodsDetail(Model model, User user, @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user", user);
        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        model.addAttribute("goods", goods);

        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();

        int seckillStatus;
        int remainSeconds;
        if (now < startTime) {
            // 秒杀还没开始，倒计时
            seckillStatus = 0;
            remainSeconds = (int) ((startTime - now) / 1000);
        } else if (now > endTime) {
            // 秒杀已经结束
            seckillStatus = 2;
            remainSeconds = -1;
        } else {
            // 秒杀进行中
            seckillStatus = 1;
            remainSeconds = 0;
        }
        model.addAttribute("seckillStatus", seckillStatus);
        model.addAttribute("remainSeconds", remainSeconds);
        return "goods_detail";
    }
}
