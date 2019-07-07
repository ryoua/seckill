package com.ryoua.seckill.service.Impl;

import com.ryoua.seckill.entity.User;
import com.ryoua.seckill.exception.GlobalException;
import com.ryoua.seckill.mapper.UserMapper;
import com.ryoua.seckill.redis.RedisService;
import com.ryoua.seckill.redis.UserKey;
import com.ryoua.seckill.result.CodeMsg;
import com.ryoua.seckill.service.UserService;
import com.ryoua.seckill.utils.Md5Util;
import com.ryoua.seckill.utils.StringUtil;
import com.ryoua.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author ryoua Created on 2019-07-07
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisService redisService;

    public static final String COOKIE_NAME_TOKEN = "token";

    public User getUserById(long id) {
        return userMapper.getUserById(id);
    }

    public String login(HttpServletResponse response, LoginVo loginVo) {
        if (loginVo == null)
            throw new GlobalException(CodeMsg.SERVER_ERROR);
        String mobile = loginVo.getMobile();
        String password = loginVo.getPassword();
        User user = getUserById(Long.parseLong(mobile));
        if (user == null)
            throw new GlobalException(CodeMsg.MOBILE_NOT_EXIST);

        // 验证密码
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = Md5Util.formPassToDBPass(password, saltDB);
        if (!calcPass.equals(dbPass))
            throw new GlobalException(CodeMsg.PASSWORD_ERROR);
        String token = StringUtil.uuid();
        addCookie(response, token, user);
        return token;
    }

    /**
     * 将token做为key，用户信息做为value 存入redis模拟session
     * 同时将token存入cookie，保存登录状态
     * @param response
     * @param token
     * @param user
     */
    public void addCookie(HttpServletResponse response, String token, User user) {
        redisService.set(UserKey.token, token, user);
        Cookie cookie = new Cookie(COOKIE_NAME_TOKEN, token);
        cookie.setMaxAge(UserKey.TOKEN_EXPIRE);
        cookie.setPath("/");
        response.addCookie(cookie);
    }
}
