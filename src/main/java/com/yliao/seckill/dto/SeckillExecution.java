package com.yliao.seckill.dto;

import com.yliao.seckill.entity.SuccessKilled;
import com.yliao.seckill.enums.SecillStatEnum;

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
     * @param successKilled
     */
    public SeckillExecution(long seckillId, SecillStatEnum statEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.info = statEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    /**
     * 秒杀失败构造器 没有秒杀成功对象
     * @param seckillId
     */
    public SeckillExecution(long seckillId, SecillStatEnum statEnum) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.info = statEnum.getStateInfo();
    }

    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + seckillId +
                ", state=" + state +
                ", info='" + info + '\'' +
                ", successKilled=" + successKilled +
                '}';
    }
}

