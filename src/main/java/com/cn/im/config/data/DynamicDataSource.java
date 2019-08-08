package com.cn.im.config.data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * 动态数据源
 *
 * @ClassName DynamicDataSource
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-08 21:43
 **/
@Component
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Autowired
    @Qualifier("rwDataSource")
    private DataSource rwDataSource;
    @Autowired
    @Qualifier("waveDataSource")
    private DataSource waveDataSource;
    @Override
    protected Object determineCurrentLookupKey() {
        return DynamicDataSourceHolder.getDataSource();
    }
    @Override
    public void afterPropertiesSet() {
        setDefaultTargetDataSource(rwDataSource);
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DynamicDataSourceGlobal.RW, rwDataSource);
        targetDataSources.put(DynamicDataSourceGlobal.WAVE, waveDataSource);
        setTargetDataSources(targetDataSources);
        super.afterPropertiesSet();
    }
}
