package com.chenx.tool;

import com.chenx.enums.DatasourceTypeEnum;

/**
 * 如果创建新的线程会导致 ThreadLocal 中的数据无法正确读取，需要额外配置：https://github.com/alibaba/transmittable-thread-local
 */
public class DatasourceContextHolder {
    private static final ThreadLocal<DatasourceTypeEnum> contextHolder = new ThreadLocal<>();

    public static void setDatasourceType(DatasourceTypeEnum type) {
        contextHolder.set(type);
    }

    public static DatasourceTypeEnum getDatasourceType() {
        return contextHolder.get();
    }

    public static void clearDatasourceType() {
        contextHolder.remove();
    }
}
