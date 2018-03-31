package com.yliao.seckill.exception;

/**
 * 秒杀相关异常
 * @Author: yliao
 * @Date: Created in 23:31 2018/3/31
 */
public class SeckillException extends RuntimeException {
    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
