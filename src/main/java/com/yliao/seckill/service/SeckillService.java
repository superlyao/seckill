package com.yliao.seckill.service;

import com.yliao.seckill.dto.Exposer;
import com.yliao.seckill.dto.SeckillExecution;
import com.yliao.seckill.entity.Seckill;
import com.yliao.seckill.exception.RepeatKillException;
import com.yliao.seckill.exception.SeckillCloseException;
import com.yliao.seckill.exception.SeckillException;

import java.util.List;

/**
 * @Author: yliao
 * @Date: Created in 23:09 2018/3/31
 * 设计接口方法三个方法:
 * 方法定义粒度
 * 参数
 * 返回类型 (类型/异常)
 */
public interface SeckillService {
    /**
     * 返回所有商品
     * @return
     */
    List<Seckill> getSeckillList();

    /**
     * 返回单个商品
     * @param seckillId
     * @return
     */
    Seckill getById(long seckillId);

    /**
     * 秒杀开启时输出秒杀接口地址，否则输出系统时间和秒杀时间
     * @param seckillId
     */
    Exposer exportSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
      * @param seckillId
     * @param userPhone
     * @param md5
     */
    SeckillExecution executeSeckill(long seckillId, long userPhone, String md5)
    throws SeckillException, RepeatKillException, SeckillCloseException;

    SeckillExecution executeSeckillProcedure(long seckillId, long userPhone, String md5);
}
