package com.ryoua.seckill.controller;

import com.ryoua.seckill.domain.SeckillOrder;
import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.rabbit.MQSender;
import com.ryoua.seckill.rabbit.SeckillMessage;
import com.ryoua.seckill.redis.GoodsKey;
import com.ryoua.seckill.redis.OrderKey;
import com.ryoua.seckill.redis.SeckillKey;
import com.ryoua.seckill.result.CodeMsg;
import com.ryoua.seckill.result.Result;
import com.ryoua.seckill.service.SeckillService;
import com.ryoua.seckill.vo.GoodsVo;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: ryoua
 * @Create: 2019-07-28 13:39
 */
@Controller
@RequestMapping("/miaosha")
public class SeckillController extends BasicController implements InitializingBean {

    @Autowired
    MQSender mqSender;

    private Map<Long, Boolean> localOverMap = new ConcurrentHashMap<Long, Boolean>();

    @RequestMapping(value = "/{path}/do_miaosha", method = RequestMethod.POST)
    @ResponseBody
    public Result<Integer> list(Model model, User user,
                                @RequestParam("goodsId") Long goodsId,
                                @PathVariable("path") String path) {
        model.addAttribute("user", user);
        if (user == null)
            return Result.error(CodeMsg.SESSION_ERROR);

        //验证path
        boolean check = seckillService.checkPath(user, goodsId, path);
        if (!check) {
            return Result.error(CodeMsg.REQUEST_ILLEGAL);
        }

        boolean over = localOverMap.get(goodsId);
        if (over) return Result.error(CodeMsg.MIAO_SHA_OVER);

        // 预减库存
        Long stock = redisService.decr(GoodsKey.getSeckillGoodsStock, "" + goodsId);
        if (stock < 0) {
            localOverMap.put(goodsId, true);
            return Result.error(CodeMsg.MIAO_SHA_OVER);
        }

        SeckillOrder order = orderService.getSeckillOrderByUserIdGoodsId(user.getId(), goodsId);
        if (order != null)
            return Result.error(CodeMsg.REPEATE_MIAOSHA);

        // 加入队列
        SeckillMessage message = new SeckillMessage();
        message.setGoodsId(goodsId);
        message.setUser(user);
        mqSender.sendSeckillMessage(message);
        return Result.success(0);
    }

    /**
     * 初始化
     *
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        if (goodsList == null) return;
        for (GoodsVo goodsVo : goodsList) {
            redisService.set(GoodsKey.getSeckillGoodsStock, "" + goodsVo.getId(), goodsVo.getStockCount());
            localOverMap.put(goodsVo.getId(), false);
        }
    }

    @RequestMapping(value = "/path", method = RequestMethod.GET)
    @ResponseBody
    public Result<String> getMiaoshaPath(HttpServletRequest request, User user,
                                         @RequestParam("goodsId") long goodsId,
                                         @RequestParam(value = "verifyCode", defaultValue = "0") int verifyCode
    ) {
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
//        boolean check = miaoshaService.checkVerifyCode(user, goodsId, verifyCode);
//        if(!check) {
//            return Result.error(CodeMsg.REQUEST_ILLEGAL);
//        }
        String path = seckillService.createSeckillPath(user, goodsId);
        return Result.success(path);
    }


    @RequestMapping(value = "/result", method = RequestMethod.GET)
    @ResponseBody
    public Result<Long> miaoshaResult(Model model, User user,
                                      @RequestParam("goodsId") long goodsId) {
        model.addAttribute("user", user);
        if (user == null) {
            return Result.error(CodeMsg.SESSION_ERROR);
        }
        long result = seckillService.getMiaoshaResult(user.getId(), goodsId);
        return Result.success(result);
    }

    @RequestMapping(value = "/reset", method = RequestMethod.GET)
    @ResponseBody
    public Result<Boolean> reset(Model model) {
        List<GoodsVo> goodsList = goodsService.listGoodsVo();
        for (GoodsVo goods : goodsList) {
            goods.setStockCount(10);
            redisService.set(GoodsKey.getSeckillGoodsStock, "" + goods.getId(), 10);
            localOverMap.put(goods.getId(), false);
        }
        redisService.delete(OrderKey.getSeckillOrderByUidGid);
        redisService.delete(SeckillKey.isGoodsOver);
        seckillService.reset(goodsList);
        return Result.success(true);
    }
}
