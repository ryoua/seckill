package com.ryoua.seckill.service;

import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.exception.GlobalException;
import com.ryoua.seckill.mapper.UserMapper;
import com.ryoua.seckill.redis.RedisService;
import com.ryoua.seckill.redis.UserKey;
import com.ryoua.seckill.result.CodeMsg;
import com.ryoua.seckill.utils.MD5Util;
import com.ryoua.seckill.utils.UUIDUtil;
import com.ryoua.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: ryoua
 * @Create: 2019-07-27 15:56
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    @Autowired
    RedisService redisService;

    public static final String COOKIE_NAME_TOKEN = "token";

    public User getById(long id) {
        return userMapper.getById(id);
    }

    public boolean login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null)
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        User user = getById(Long.parseLong(mobile));
        if (user == null)
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass))
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        addCookie(response, user) ;
        return true;
    }

    public User getByToken(HttpServletResponse response, String token) {
        if (StringUtils.isEmpty(token))
            return null;
        User user = redisService.get(UserKey.token, token, User.class);
        // 延迟Session的有效期
        if (user != null)
            addCookie(response, user);
        return user;
    }

    private void addCookie(HttpServletResponse response, User user) {
        String token = UUIDUtil.uuid();
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(UserKey.token.expireSeconds());
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
