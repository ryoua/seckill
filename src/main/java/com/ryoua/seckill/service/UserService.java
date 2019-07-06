package com.ryoua.seckill.service;

import com.ryoua.seckill.entity.User;
import com.ryoua.seckill.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ryoua Created on 2019-07-06
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(int id) {
        return userMapper.getUserById(id);
    }
}
