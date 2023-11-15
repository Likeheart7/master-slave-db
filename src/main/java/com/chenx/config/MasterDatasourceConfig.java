package com.chenx.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@ConditionalOnProperty("spring.datasource.master.jdbc-url")
public class MasterDatasourceConfig {
    @Bean("masterDatasource")
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDatasource(){
        return DataSourceBuilder.create().build();
    }

}
