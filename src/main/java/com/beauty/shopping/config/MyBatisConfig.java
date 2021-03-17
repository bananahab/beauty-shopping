package com.beauty.shopping.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author wuzhenxian
 * @date 2021/03/03
 * Mybatis 配置类
 */
@Configuration
@MapperScan("com.beauty.shopping.dao")
public class MyBatisConfig {

}
