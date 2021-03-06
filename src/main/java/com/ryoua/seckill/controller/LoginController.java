package com.ryoua.seckill.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.ryoua.seckill.result.Result;
import com.ryoua.seckill.vo.LoginVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * @Author: ryoua
 * @Create: 2019-07-27 14:17
 */
@Controller
@RequestMapping("/login")
public class LoginController extends BasicController {

    private static Logger log = LoggerFactory.getLogger(LoginController.class);


    @RequestMapping("/to_login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/do_login")
    @ResponseBody
    public Result<String> doLogin(HttpServletResponse response, @Valid LoginVo loginVo) {
        log.info(loginVo.toString());
        String token = userService.login(response, loginVo);
        return Result.success(token);
    }
}
