package com.ryoua.seckill.controller;

import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.redis.RedisService;
import com.ryoua.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String list(Model model, @CookieValue(value = "token", required = false) String cookieToken,
                       @RequestParam(value = "token", required = false) String paramToken) {
        if (StringUtils.isEmpty(cookieToken) && StringUtils.isEmpty(paramToken))
            return "login";
        String token = StringUtils.isEmpty(paramToken) ? cookieToken : paramToken;
        User user = userService.getByToken(token);
        model.addAttribute("user", user);
        return "goods_list";
    }

}
