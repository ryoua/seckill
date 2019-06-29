package com.ryoua.seckill.dao;

import com.ryoua.seckill.BaseTest;
import com.ryoua.seckill.entity.Seckill;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;


/**
 * @Author ryoua Created on 2019-06-29
 */
public class SeckillDaoTest extends BaseTest {

    //注入Dao实现类依赖
    @Resource
    private SeckillDao seckillDao;

    @Test
    public void testQueryById() throws Exception {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill.getName());
        System.out.println(seckill);
    }

    @Test
    public void testQueryAll() throws Exception  {
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }

    @Test
    public void testReduceNumber() throws Exception {
        Date killTime = new Date();
        int updateCount = seckillDao.reduceNumber(1000L, killTime);
        System.out.println("updateCount=" + updateCount);
    }

}