package com.ryoua.seckill.vo;

import com.ryoua.seckill.domain.User;

/**
 * @Author: ryoua
 * @Create: 2019-08-03 09:06
 */
public class GoodsDetailVo {
    private Integer remainSeconds;
    private Integer seckillStatus;
    private GoodsVo goodsVo;
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getRemainSeconds() {
        return remainSeconds;
    }

    public void setRemainSeconds(Integer remainSeconds) {
        this.remainSeconds = remainSeconds;
    }

    public Integer getSeckillStatus() {
        return seckillStatus;
    }

    public void setSeckillStatus(Integer seckillStatus) {
        this.seckillStatus = seckillStatus;
    }

    public GoodsVo getGoodsVo() {
        return goodsVo;
    }

    public void setGoodsVo(GoodsVo goodsVo) {
        this.goodsVo = goodsVo;
    }
}
