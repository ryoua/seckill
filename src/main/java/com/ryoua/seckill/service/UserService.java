package com.ryoua.seckill.service;

import com.ryoua.seckill.entity.User;
import com.ryoua.seckill.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
