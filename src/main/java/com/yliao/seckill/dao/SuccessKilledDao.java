package com.yliao.seckill.dao;

import com.yliao.seckill.entity.SuccessKilled;

public interface SuccessKilledDao {
    /**
     * 插入购买明细
     * 可过滤重复
     * @param seckillId
     * @param userPhone
     * @return
     */
    int insterSuccessKilled(long seckillId, long userPhone);

    SuccessKilled queryByIdWithSeckill(long seckillId);
}
