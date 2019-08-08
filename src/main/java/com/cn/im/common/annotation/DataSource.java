package com.cn.im.common.annotation;

import com.cn.im.config.data.DynamicDataSourceGlobal;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface DataSource {

    DynamicDataSourceGlobal value() default DynamicDataSourceGlobal.RW;
}
