package com.yliao.seckill.dao;

import com.yliao.seckill.base.TestConfig;
import com.yliao.seckill.entity.SuccessKilled;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

public class SuccessKilledDaoTest extends TestConfig {

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Test
    public void insertSuccessKilled() {
        int i = successKilledDao.insertSuccessKilled(1001, 13800000000L);
        System.out.println(i);
    }

    @Test
    public void queryByIdWithSeckill() {
        SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(1001, 13800000000L);
        System.out.println(successKilled);
        System.out.println(successKilled.getSeckill());
    }
}