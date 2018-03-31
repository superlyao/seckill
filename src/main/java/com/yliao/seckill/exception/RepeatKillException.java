package com.yliao.seckill.exception;


/**
 * 重复秒杀异常 spring声明事务只支持运行时异常
 * @Author: yliao
 * @Date: Created in 23:28 2018/3/31
 */
public class RepeatKillException extends SeckillException {

    public RepeatKillException(String message) {
        super(message);
    }

    public RepeatKillException(String message, Throwable cause) {
        super(message, cause);
    }
}
