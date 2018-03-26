package com.yliao.seckill.dao;

import com.yliao.seckill.entity.Seckill;

import java.util.Date;
import java.util.List;

public interface SeckillDao {
    /**
     * 减库存
     * @param seckillId
     * @param killTime
     * @return
     */
    int reduceNumber(long seckillId, Date killTime);

    /**
     * 根据id查询
     * @param seckillId
     * @return
     */
    Seckill queryById(long seckillId);

    /**
     * 查询所有
     * @param offet 偏移量
     * @param limit 条数
     * @return
     */
    List<Seckill> queryAll(int offet, int limit);
}
