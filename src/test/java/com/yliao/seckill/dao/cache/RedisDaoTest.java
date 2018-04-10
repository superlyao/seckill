package com.yliao.seckill.dao.cache;

import com.yliao.seckill.base.TestConfig;
import com.yliao.seckill.dao.SeckillDao;
import com.yliao.seckill.entity.Seckill;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.Assert.*;

/**
 * @Author: yliao
 * @Date: Created in 2018/4/10
 */
public class RedisDaoTest extends TestConfig {
    private long id = 1000;

    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void testSeckill() {
        Seckill seckill  = redisDao.getSeckill(id);
        if (null == seckill) {
            seckill = seckillDao.queryById(id);
            if (null != seckill) {
                String s = redisDao.putSeckill(seckill);
                System.out.println(s);
                Seckill seckill1 = redisDao.getSeckill(id);
                System.out.println(seckill1);
            }
        }
    }

}