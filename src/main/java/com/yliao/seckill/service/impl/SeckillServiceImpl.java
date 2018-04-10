package com.yliao.seckill.service.impl;

import com.yliao.seckill.dao.SeckillDao;
import com.yliao.seckill.dao.SuccessKilledDao;
import com.yliao.seckill.dao.cache.RedisDao;
import com.yliao.seckill.dto.Exposer;
import com.yliao.seckill.dto.SeckillExecution;
import com.yliao.seckill.entity.Seckill;
import com.yliao.seckill.entity.SuccessKilled;
import com.yliao.seckill.enums.SeckillStatEnum;
import com.yliao.seckill.exception.RepeatKillException;
import com.yliao.seckill.exception.SeckillCloseException;
import com.yliao.seckill.exception.SeckillException;
import com.yliao.seckill.service.SeckillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * @Author: yliao
 * @Date: Created in 15:00 2018/4/1
 */
@Service
public class SeckillServiceImpl implements SeckillService {
    private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);
    // 注入service依赖
    @Autowired
    private SeckillDao seckillDao;

    @Autowired
    private SuccessKilledDao successKilledDao;

    @Autowired
    private RedisDao redisDao;

    // 用于混淆MD5
    private static final String slat = "aqwddaslk@@#!@#!@#!";

    public List<Seckill> getSeckillList() {
        return seckillDao.queryAll(0, 4);
    }

    public Seckill getById(long seckillId) {
        return seckillDao.queryById(seckillId);
    }


    public Exposer exportSeckillUrl(long seckillId) {
        // 优化点： 缓存优化:超时的基础维护一致性
        // 访问redis
        Seckill seckill = redisDao.getSeckill(seckillId);
        if (null == seckill) { //如果为空访问数据库
            seckill = seckillDao.queryById(seckillId);
            if (seckill == null) { // 数据为空直接失败
                return new Exposer(false, seckillId);
            } else {
                //放入redis
                redisDao.putSeckill(seckill);
            }
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

    /**
     * 使用注解控制事务的优点:
     * 1 开发团队一致性约定，明确标注事务方法的编程风格
     * 2 保证事务方法的执行时间尽量的短 不要穿插其他的网络操作， 或则剥离在方法外部
     * 3 不是所有的方法都需要事务 如只有一条修改操作，只读操作不需要事务
     * @param seckillId
     * @param userPhone
     * @param md5
     * @return
     * @throws SeckillException
     * @throws RepeatKillException
     * @throws SeckillCloseException
     */
    @Transactional
    public SeckillExecution executeSeckill(long seckillId, long userPhone, String md5) throws SeckillException, RepeatKillException, SeckillCloseException {
        try {
            if (md5 == null || !md5.equals(getMd5(seckillId))) {
                throw new SeckillException("秒杀失败");
            }
            // 执行秒杀逻辑 减库存+记录秒杀行为
            Date now = new Date();
            int number = seckillDao.reduceNumber(seckillId, now);
            if (number <= 0) {
                // 没有更新记录
                throw new SeckillCloseException("秒杀以结束");
            } else {
                // 减库存成功 记录购买行为
                int insertCount = successKilledDao.insertSuccessKilled(seckillId, userPhone);
                if (insertCount <= 0) {
                    throw new RepeatKillException("重复秒杀");
                } else {
                    // 秒杀成功
                    SuccessKilled successKilled = successKilledDao.queryByIdWithSeckill(seckillId, userPhone);
                    return new SeckillExecution(seckillId, SeckillStatEnum.SUCCESS, successKilled);
                }
            }
        }catch (SeckillCloseException e1) {
            throw e1;
        }catch (RepeatKillException e2) {
            throw e2;
        }catch (Exception e) {
            logger.error(e.getMessage(), e);
            // 转化为运行期异常
            throw new SeckillException("内部错误" + e.getMessage());
        }
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
