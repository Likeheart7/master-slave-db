package com.chenx.config;

import com.chenx.enums.DatasourceTypeEnum;
import com.chenx.tool.DynamicRoutingDatasource;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;
import java.util.HashMap;

/**
 * 将主数据库和从数据库的数据源添加到动态数据源中，
 * 并可以通过枚举创建一个数据源 map，这样就可以通过路由返回的枚举来切换数据源
 */
@Configuration
@ConditionalOnProperty("spring.datasource.master.jdbc-url")
public class DynamicDatasourceConfiguration {
    @Bean("datasource")
    @Primary
    public DataSource dynamicDatasource(DataSource masterDatasource, DataSource slaveDatasource) {
        HashMap<Object, Object> targetDatasourceMap = new HashMap<>();
        targetDatasourceMap.put(DatasourceTypeEnum.MASTER, masterDatasource);
        targetDatasourceMap.put(DatasourceTypeEnum.SLAVE, slaveDatasource);

        DynamicRoutingDatasource dynamicRoutingDatasource = new DynamicRoutingDatasource();
        dynamicRoutingDatasource.setTargetDataSources(targetDatasourceMap);
        dynamicRoutingDatasource.setDefaultTargetDataSource(masterDatasource);
        return dynamicRoutingDatasource;
    }
}
