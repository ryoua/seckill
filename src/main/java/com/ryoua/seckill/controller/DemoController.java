package com.ryoua.seckill.controller;

import com.ryoua.seckill.entity.User;
import com.ryoua.seckill.redis.RedisService;
import com.ryoua.seckill.result.CodeMsg;
import com.ryoua.seckill.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试路由
 * @Author ryoua Created on 2019-07-06
 */
@Controller
@RequestMapping("/demo")
public class DemoController {

    @Autowired
    private RedisService redisService;

    @RequestMapping("/")
    @ResponseBody
    public String home() {
        return "Hello World";
    }

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> hello() {
        return Result.success("hello, RyouA");
    }

    @RequestMapping("/Error")
    @ResponseBody
    public Result<String> error(){
        return Result.error(CodeMsg.SERVER_ERROR);
    }

    @RequestMapping("/Thymeleaf")
    public String thymeleaf(Model model){
        model.addAttribute("name", "ryoua");
        return "hello";
    }

    @RequestMapping("/redis/get")
    @ResponseBody
    public Result<String> redisGet(){
        String l1 = redisService.get("key1", String.class);
        return Result.success(l1);
    }

    @RequestMapping("/redis/set")
    @ResponseBody
    public Result<Boolean> redisSet(){
        Boolean b1 = redisService.set("key1", "hello, ryoua");
        return Result.success(b1);
    }

}
