package com.yliao.seckill.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: yliao
 * @Date: Created in 22:33 2018/4/1
 */
public class LoggerUtil {
    private static Logger logger;
    /**
     * info级别
     * @param clazz
     * @param context
     * @param params
     */
    public static void info(Class<?> clazz, String context, Object...params){
        logger = LoggerFactory.getLogger(clazz);
        logger.info(context, params);
    }
    /**
     * info级别（含方法名）
     * @param clazz
     * @param methodName
     * @param context
     * @param params
     */
    public static void info(Class<?> clazz, String methodName, String context, Object...params){
        context = "methodName=>("+methodName+");info->"+context;
        info(clazz, context, params);
    }
    /**
     * error级别
     * @param clazz
     * @param context
     * @param params
     */
    public static void error(Class<?> clazz, String context, Object...params){
        logger = LoggerFactory.getLogger(clazz);
        logger.error(context, params);
    }
    /**
     * error级别（含方法名）
     * @param clazz
     * @param methodName
     * @param context
     * @param params
     */
    public static void error(Class<?> clazz, String methodName, String context, Object...params){
        context = "methodName=>("+methodName+");error->"+context;
        error(clazz, context, params);
    }
    /**
     * 记录异常
     * @param clazz
     * @param context
     * @param e
     */
    public static void error(Class<?> clazz, String context, Exception e){
        logger =LoggerFactory.getLogger(clazz);
        logger.error(context, e);
    }


}
