package com.chenx.aop;

import com.chenx.enums.DatasourceTypeEnum;
import com.chenx.tool.DatasourceContextHolder;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 * 创建切面类，用于拦截数据库请求，根据配置切换数据源
 */
@Aspect
@Component
public class DatasourceAspect {
    @Before("@annotation(com.chenx.annotations.MasterDatasource)")
    public void setMasterDatasource(JoinPoint joinPoint) {
        DatasourceContextHolder.setDatasourceType(DatasourceTypeEnum.MASTER);
    }

    @Before("@annotation(com.chenx.annotations.SlaveDatasource)")
    public void setSlaveDatasource(JoinPoint joinPoint) {
        DatasourceContextHolder.setDatasourceType(DatasourceTypeEnum.SLAVE);
    }

    @After("@annotation(com.chenx.annotations.MasterDatasource) || @annotation(com.chenx.annotations.SlaveDatasource)")
    public void clearDatasource(JoinPoint joinPoint){
        DatasourceContextHolder.clearDatasourceType();
    }
}
