package com.ryoua.seckill.service.Impl;

import com.ryoua.seckill.entity.User;
import com.ryoua.seckill.mapper.UserMapper;
import com.ryoua.seckill.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ryoua Created on 2019-07-07
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;

    public User getUserById(long id) {
        return userMapper.getUserById(id);
    }
}
