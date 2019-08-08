package com.cn.im.config.data;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @ClassName DataSourceConfig
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-08 22:11
 **/
@Configuration
public class DataSourceConfig {

    /**
     * 写数据库
     *
     * @return 数据源
     */
    @Primary
    @Bean(name = "rwDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.rw")
    public DataSource writeDataSource() {
        return DataSourceBuilder.create().build();
    }
}
