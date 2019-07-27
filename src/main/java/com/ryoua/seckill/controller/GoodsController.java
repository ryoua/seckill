package com.ryoua.seckill.controller;

import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.redis.RedisService;
import com.ryoua.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


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
    RedisService redisService;

    @RequestMapping("/to_list")
    public String list(Model model,User user) {
        model.addAttribute("user", user);
        return "goods_list";
    }

}
