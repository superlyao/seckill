package com.yliao.seckill.service.impl;

import com.yliao.seckill.dao.SeckillDao;
import com.yliao.seckill.dao.SuccessKilledDao;
import com.yliao.seckill.dto.Exposer;
import com.yliao.seckill.dto.SeckillExecution;
import com.yliao.seckill.entity.Seckill;
import com.yliao.seckill.exception.RepeatKillException;
import com.yliao.seckill.exception.SeckillCloseException;
import com.yliao.seckill.exception.SeckillException;
import com.yliao.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: yliao
 * @Date: Created in 15:00 2018/4/1
 */
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    // 用于混淆MD5
    private static final String slat = "aqwddaslk@@#!@#!@#!";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }

    public Exposer exportSeckillUrl(long seckillId) {
        Seckill seckill = seckillDao.queryById(seckillId);
        if (seckill == null) {
            return new Exposer(false, seckillId);
        }

        Date start = seckill.getStartTime();
        Date end = seckill.getEndTime();

        // 系统当前时间
        Date nowTime = new Date();
        if (nowTime.getTime() < start.getTime() || nowTime.getTime() > end.getTime()) {
            return new Exposer(false, seckillId, nowTime.getTime(), start.getTime(), end.getTime());
        }
        String md5 = getMd5(seckillId);
        return new Exposer(true, md5, seckillId);
    }

    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        return null;
    }

    /**
     * 生成MD5
     * @param seckillId
     * @return
     */
    private String getMd5(long seckillId) {
        String base = seckillId + "/" + slat;
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
