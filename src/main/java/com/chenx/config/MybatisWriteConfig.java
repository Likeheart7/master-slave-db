package com.chenx.config;

import com.chenx.enums.DatasourceTypeEnum;
import com.chenx.tool.DatasourceContextHolder;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 通过mybatis拦截器配置写入强制主库
 */
@Intercepts({
        @Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
})
@Component
public class MybatisWriteConfig implements Interceptor {
    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        // 获取 SQL 类型
        DatasourceTypeEnum dataSourceType = DatasourceContextHolder.getDatasourceType();
        if(DatasourceTypeEnum.SLAVE.equals(dataSourceType)) {
            DatasourceContextHolder.setDatasourceType(DatasourceTypeEnum.MASTER);
        }
        try {
            // 执行 SQL
            return invocation.proceed();
        } finally {
            // 恢复数据源  考虑到写入后可能会反查，后续都走主库
            // DataSourceContextHolder.setDataSourceType(dataSourceType);
        }
    }

}
