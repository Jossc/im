package com.cn.im.common.base;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


/**
 * @ClassName SpringContext
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-10 21:28
 **/
@Component
public class SpringContext  implements ApplicationContextAware {
    private static SpringContext self;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

    }
}
