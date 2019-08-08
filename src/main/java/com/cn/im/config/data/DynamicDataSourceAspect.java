package com.cn.im.config.data;

import com.cn.im.common.annotation.DataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



/**
 * @ClassName DynamicDataSourceAspect
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-08 21:45
 **/
@Aspect
@Order(-1)
@Component
public class DynamicDataSourceAspect {

    @Before("@annotation(ds)")
    public void before(JoinPoint point, DataSource ds) {
        DynamicDataSourceHolder.putDataSource(ds.value());
    }
    @After("@annotation(ds)")
    public void after(JoinPoint point, DataSource ds) {
        DynamicDataSourceHolder.clearDataSource();
    }
}
