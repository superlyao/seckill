package com.yliao.seckill.exception;

/**
 * 秒杀关闭异常
 * @Author: yliao
 * @Date: Created in 23:31 2018/3/31
 */
public class SeckillCloseException extends SeckillException {
    public SeckillCloseException(String message) {
        super(message);
    }

    public SeckillCloseException(String message, Throwable cause) {
        super(message, cause);
    }
}
