package com.yliao.seckill.dto;

import com.yliao.seckill.entity.SuccessKilled;
import com.yliao.seckill.enums.SeckillStatEnum;

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

    public SeckillExecution() {}


    /**
     * 秒杀成功构造器
     * @param seckillId
     * @param successKilled
     */
    public SeckillExecution(long seckillId, SeckillStatEnum statEnum, SuccessKilled successKilled) {
        this.seckillId = seckillId;
        this.state = statEnum.getState();
        this.info = statEnum.getStateInfo();
        this.successKilled = successKilled;
    }

    /**
     * 秒杀失败构造器 没有秒杀成功对象
     * @param seckillId
     */
    public SeckillExecution(long seckillId, SeckillStatEnum statEnum) {
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

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public SuccessKilled getSuccessKilled() {
        return successKilled;
    }

    public void setSuccessKilled(SuccessKilled successKilled) {
        this.successKilled = successKilled;
    }
}

