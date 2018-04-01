package com.yliao.seckill.base;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: yliao
 * @Date: Created in 22:19 2018/3/31
 */
@RunWith(SpringJUnit4ClassRunner.class) //junit启动时加载springIOC容器
@ContextConfiguration({
        "classpath:spring/spring-dao.xml",
        "classpath:spring/spring-service.xml"
}) //加载配置文件
public class TestConfig {
}
