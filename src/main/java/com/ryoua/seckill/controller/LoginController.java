package com.ryoua.seckill.controller;

import com.ryoua.seckill.result.Result;
import com.ryoua.seckill.service.UserService;
import com.ryoua.seckill.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

/**
 * @Author ryoua Created on 2019-07-06
 */
@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private UserService userService;
    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        logger.info(loginVo.toString());
        String token = userService.login(response, loginVo);
        return Result.success(token);
    }
}
