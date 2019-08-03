package com.ryoua.seckill.controller;

import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.redis.GoodsKey;
import com.ryoua.seckill.redis.RedisService;
import com.ryoua.seckill.result.Result;
import com.ryoua.seckill.service.GoodsService;
import com.ryoua.seckill.service.UserService;
import com.ryoua.seckill.vo.GoodsDetailVo;
import com.ryoua.seckill.vo.GoodsVo;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.spring4.context.SpringWebContext;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;

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

    @Autowired
    ThymeleafViewResolver thymeleafViewResolver;

    @Autowired
    ApplicationContext applicationContext;

    @RequestMapping(value = "/to_list", produces = "text/html")
    @ResponseBody
    public String list(HttpServletRequest request,
                       HttpServletResponse response,
                       Model model,
                       User user) {
        model.addAttribute("user", user);
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        model.addAttribute("goodsList", goodsList);
        String html = redisService.get(GoodsKey.getGoodsList, "", String.class);
        if (!StringUtils.isEmpty(html))
            return html;
        SpringWebContext ctx = new SpringWebContext(request, response,
                request.getServletContext(), request.getLocale(), model.asMap(), applicationContext);
        html = thymeleafViewResolver.getTemplateEngine().process("goods_list", ctx);
        if (!StringUtils.isEmpty(html))
            redisService.set(GoodsKey.getGoodsList, "", html);
        return html;
    }

    @RequestMapping(value = "/to_detail/{goodsId}", produces = "text/html")
    @ResponseBody
    public String detail(HttpServletRequest request,
                         HttpServletResponse response,
                         Model model,
                         User user,
                         @PathVariable("goodsId") long goodsId) {
        model.addAttribute("user", user);
        //取缓存
        String html = redisService.get(GoodsKey.getGoodsDetail, "" + goodsId, String.class);
        if (!StringUtils.isEmpty(html)) {
            return html;
        }
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
        } else if (now > endTime) {
            status = 2;
            remainSeconds = -1;
        } else {
            status = 1;
            remainSeconds = 0;
        }
        model.addAttribute("miaoshaStatus", status);
        model.addAttribute("remainSeconds", remainSeconds);
        SpringWebContext ctx = new SpringWebContext(request, response,
                request.getServletContext(), request.getLocale(), model.asMap(), applicationContext);
        html = thymeleafViewResolver.getTemplateEngine().process("goods_detail", ctx);
        if (!StringUtils.isEmpty(html)) {
            redisService.set(GoodsKey.getGoodsDetail, "" + goodsId, html);
        }
        return html;
    }


    @RequestMapping(value = "/to_detail2/{goodsId}", produces = "text/html")
    @ResponseBody
    public Result<GoodsDetailVo> detail2(User user,
                                         @PathVariable("goodsId") long goodsId) {

        GoodsVo goods = goodsService.getGoodsVoByGoodsId(goodsId);
        long startTime = goods.getStartDate().getTime();
        long endTime = goods.getEndDate().getTime();
        long now = System.currentTimeMillis();
        int status;
        int remainSeconds;
        if (now < startTime) {
            status = 0;
            remainSeconds = (int) ((startTime - now) / 1000);
        } else if (now > endTime) {
            status = 2;

            remainSeconds = -1;
        } else {
            status = 1;
            remainSeconds = 0;
        }
        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();
        goodsDetailVo.setGoodsVo(goods);
        goodsDetailVo.setRemainSeconds(remainSeconds);
        goodsDetailVo.setSeckillStatus(status);
        goodsDetailVo.setUser(user);
        return Result.success(goodsDetailVo);
    }
}
