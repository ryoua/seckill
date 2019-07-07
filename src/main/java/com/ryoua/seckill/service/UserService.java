package com.ryoua.seckill.service;

import com.ryoua.seckill.entity.User;
import com.ryoua.seckill.vo.LoginVo;

import javax.servlet.http.HttpServletResponse;

/**
 * @Author ryoua Created on 2019-07-06
 */
public interface UserService {

    /**
     * 根据id找到用户
     * @param id
     * @return
     */
    public User getUserById(long id);

    String login(HttpServletResponse response, LoginVo loginVo);
}
