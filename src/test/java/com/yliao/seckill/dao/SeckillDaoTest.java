package com.yliao.seckill.dao;

import com.yliao.seckill.entity.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sound.midi.Soundbank;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合
 * junit启动时加载springIOC容器
 */
@RunWith(SpringJUnit4ClassRunner.class) //junit启动时加载springIOC容器
@ContextConfiguration({"classpath:spring/spring-dao.xml"}) //加载配置文件
public class SeckillDaoTest {
    @Autowired
    private SeckillDao seckillDao;

    @Test
    public void reduceNumber() {

    }

    @Test
    public void queryById() {
        long id = 1000;
        Seckill seckill = seckillDao.queryById(id);
        System.out.println(seckill);
    }

    @Test
    public void queryAll() {
    }
}