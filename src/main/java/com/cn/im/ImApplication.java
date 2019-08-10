package com.cn.im;

import com.cn.im.common.base.BeanProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class ImApplication {

    public static void main(String[] args) {
        BeanProvider.initialize(SpringApplication.run(ImApplication.class, args));
    }

}
