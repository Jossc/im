package com.cn.im.common.yml;

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;

import java.io.IOException;
import java.util.Properties;

/**
 * 自定义加载yml文件
 *
 * @ClassName YamlPropertyLoaderFactory
 * @Author chenzhuo
 * @Version 1.0
 * @Date 2019-08-11 15:21
 **/
public class YamlPropertyLoaderFactory extends DefaultPropertySourceFactory {

    @Override
    public PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException, IOException {
        String sourceName = name != null ? name : resource.getResource().getFilename();
        if (!resource.getResource().exists()) {
            return new PropertiesPropertySource(sourceName, new Properties());
        } else if (sourceName.endsWith(".yml") || sourceName.endsWith(".yaml")) {
            Properties propertiesFromYaml = loadYml(resource);
            return new PropertiesPropertySource(sourceName, propertiesFromYaml);
        } else {
            return super.createPropertySource(name, resource);
        }
    }

    /**
     * 加载yml文件
     *
     * @param resource
     * @return
     * @throws IOException
     */
    private Properties loadYml(EncodedResource resource) throws IOException {
        YamlPropertiesFactoryBean factory = new YamlPropertiesFactoryBean();
        factory.setResources(resource.getResource());
        factory.afterPropertiesSet();
        return factory.getObject();
    }
}
