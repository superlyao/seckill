package com.yliao.seckill.dto;

import com.yliao.seckill.entity.SuccessKilled;

/**
 * @Author: yliao
 * @Date: Created in 23:23 2018/3/31
 */
public class SeckillExecution {
    private long seckillId;

    // 秒杀结果状态
    private int state;

    // 状态表示
    private String info;

    // 秒杀成功对象
    private SuccessKilled successKilled;

    /**
     * 秒杀成功构造器
     * @param seckillId
     * @param state
     * @param info
     * @param successKilled
     */
    public SeckillExecution(long seckillId, int state, String info, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = state;
        this.info = info;
        this.successKilled = successKilled;
    }

    /**
     * 秒杀失败构造器 没有秒杀成功对象
     * @param seckillId
     * @param state
     * @param info
     */
    public SeckillExecution(long seckillId, int state, String info) {
        this.seckillId = seckillId;
        this.state = state;
        this.info = info;
    }
}

