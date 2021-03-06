package com.ryoua.seckill.domain;

import java.util.Date;

/**
 * @Author: ryoua
 * @Create: 2019-07-28 12:53
 */
public class SeckillGoods {
    private Double seckillPrice;
    private Long id;
    private Long goodsId;
    private Integer stockCount;
    private Date startDate;
    private Date endDate;
    public Double getSeckillPrice() {
        return seckillPrice;
    }
    public void setSeckillPrice(Double seckillPrice) {
        this.seckillPrice = seckillPrice;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public Long getGoodsId() {
        return goodsId;
    }
    public void setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
    }
    public Integer getStockCount() {
        return stockCount;
    }
    public void setStockCount(Integer stockCount) {
        this.stockCount = stockCount;
    }
    public Date getStartDate() {
        return startDate;
    }
    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }
    public Date getEndDate() {
        return endDate;
    }
    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }
}
