package com.yliao.seckill.dao;

import com.yliao.seckill.base.TestConfig;
import com.yliao.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sound.midi.Soundbank;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合
 * junit启动时加载springIOC容器
 */
public class SeckillDaoTest extends TestConfig {
    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() {
        Date date = new Date();
        int updateCount = seckillDao.reduceNumber(1000, date);
        System.out.print("updateCount:" + updateCount);
    }

    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill);
    }

    @Test
    public void queryAll() {
        List<Seckill> seckills = seckillDao.queryAll(0, 100);
        for (Seckill seckill : seckills) {
            System.out.println(seckill);
        }
    }
}