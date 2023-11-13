package com.chenx.tool;

import com.chenx.enums.DatasourceTypeEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class DynamicRoutingDatasource extends AbstractRoutingDataSource {

    @Value("${DB_RW_SEPARATE_SWITCH:false}")
    private boolean dbRwSeparateSwitch;

    @Override
    public Object determineCurrentLookupKey() {
        if(dbRwSeparateSwitch && DatasourceContextHolder.getDatasourceType().equals(DatasourceTypeEnum.SLAVE)){
            log.info("DynamicRoutingDatasource 切换数据源到从库");
            return DatasourceTypeEnum.SLAVE;
        }
        log.info("DynamicRoutingDatasource 切换数据源到主库");
        return DatasourceTypeEnum.MASTER;
    }
}
