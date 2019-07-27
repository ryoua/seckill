package com.ryoua.seckill.service;

import com.ryoua.seckill.domain.User;
import com.ryoua.seckill.mapper.UserMapper;
import com.ryoua.seckill.result.CodeMsg;
import com.ryoua.seckill.utils.MD5Util;
import com.ryoua.seckill.vo.LoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: ryoua
 * @Create: 2019-07-27 15:56
 */
@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public User getById(long id) {
        return userMapper.getById(id);
    }

    public CodeMsg login(LoginVo loginVo) {
        if (loginVo == null)
            return CodeMsg.SERVER_ERROR;
        String mobile = loginVo.getMobile();
        String formPass = loginVo.getPassword();
        User user = getById(Long.parseLong(mobile));
        if (user == null)
            return CodeMsg.MOBILE_NOT_EXIST;
        String dbPass = user.getPassword();
        String saltDB = user.getSalt();
        String calcPass = MD5Util.formPassToDBPass(formPass, saltDB);
        if (!calcPass.equals(dbPass))
            return CodeMsg.PASSWORD_ERROR;
        return CodeMsg.SUCCESS;
    }

}
