package com.ryoua.seckill.dao;

import com.ryoua.seckill.BaseTest;
import com.ryoua.seckill.entity.SuccessKilled;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * @Author ryoua Created on 2019-06-29
 */
public class SuccessKilledDaoTest extends BaseTest {

    @Resource
    private SuccessKilledDao successKilledDao;

    @Test
    public void testInsertSuccessKilled() throws Exception {
        long id = 1001;
        long phone = 13631231234L;
        int insertCount = successKilledDao.insertSuccessKilled(id, phone);
        System.out.println("insertCount=" + insertCount);
    }

    @Test
    public void testQueryByIdWithSeckill() throws Exception {
        long id = 1001;
        long phone = 13631231234L;
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(id, phone);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }

}
