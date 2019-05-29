package com.jzt.sso.common.mybatis.commons;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * @author RocLiu [apedad@qq.com]
 * @version 1.0
 */
public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    @Override protected Object determineCurrentLookupKey() {
        return DynamicDataSourceContextHolder.get();
    }
}
