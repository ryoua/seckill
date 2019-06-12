package com.ryoua.seckill.entity;

import java.util.Date;

/**
 * 秒杀成功实体
 * @Author ryoua Created on 2019-06-12
 */
public class SuccessKilled {
    private long seckillId;
    private long userPhone;
    private short state;
    private Date creteTime;

    public SuccessKilled() {}

    public SuccessKilled(long seckillId, long userPhone, short state, Date creteTime) {
        this.seckillId = seckillId;
        this.userPhone = userPhone;
        this.state = state;
        this.creteTime = creteTime;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public long getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(long userPhone) {
        this.userPhone = userPhone;
    }

    public short getState() {
        return state;
    }

    public void setState(short state) {
        this.state = state;
    }

    public Date getCreteTime() {
        return creteTime;
    }

    public void setCreteTime(Date creteTime) {
        this.creteTime = creteTime;
    }

    @Override
    public String toString() {
        return "SuccessKilled{" +
                "seckillId=" + seckillId +
                ", userPhone=" + userPhone +
                ", state=" + state +
                ", creteTime=" + creteTime +
                '}';
    }
}
