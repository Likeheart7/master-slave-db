package com.chenx.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

/**
 * 从库数据源配置
 * 当 @ConfigurationProperties 和 @Bean 结合使用时会将配置文件中的属性赋给@Bean的返回值
 */
@Configuration
@ConditionalOnProperty("spring.datasource.slave.jdbc-url")
public class SlaveApplicationConfig {
    @Bean("slaveDatasource")
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDatasource() {
        return DataSourceBuilder.create().build();
    }
}
