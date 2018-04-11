package com.yliao.seckill.service;

import com.yliao.seckill.base.TestConfig;
import com.yliao.seckill.dto.Exposer;
import com.yliao.seckill.dto.SeckillExecution;
import com.yliao.seckill.entity.Seckill;
import com.yliao.seckill.exception.RepeatKillException;
import com.yliao.seckill.exception.SeckillCloseException;
import com.yliao.seckill.log.LoggerUtil;
import com.yliao.seckill.service.impl.SeckillServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author: yliao
 * @Date: Created in 17:56 2018/4/1
 */
public class SeckillServiceTest extends TestConfig {

    private Logger logger = LoggerFactory.getLogger(SeckillServiceImpl.class);

    @Autowired
    private SeckillService seckillService;
    @Test
    public void getSeckillList() {
        List<Seckill> seckillList = seckillService.getSeckillList();
        logger.info("list{}",seckillList);
    }

    @Test
    public void getById() {
        Seckill byId = seckillService.getById(1000);
        logger.info("byid{}",byId.toString());
    }

    @Test
    public void exportSeckillUrl() {
        Exposer exposer = seckillService.exportSeckillUrl(1000);
        logger.info("expose{}", exposer);
    }

    @Test
    public void executeSeckill() {
        try {
            SeckillExecution seckillExecution = seckillService.executeSeckill(1000, 13800000001L, "5eea866acde5d5c8571fbb8566c2b44a");
            logger.info("result{}", seckillExecution);
        } catch (RepeatKillException e) {
            LoggerUtil.error(this.getClass(),e.getMessage(), e);
        }catch (SeckillCloseException e1) {
            LoggerUtil.error(this.getClass(),e1.getMessage(), e1);
        }
    }
}