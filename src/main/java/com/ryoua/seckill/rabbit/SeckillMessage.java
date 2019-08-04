package com.ryoua.seckill.rabbit;

import com.ryoua.seckill.domain.User;

/**
 * @Author: ryoua
 * @Create: 2019-08-04 13:35
 */
public class SeckillMessage {
    private User user;
    private Long goodsId;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
}
