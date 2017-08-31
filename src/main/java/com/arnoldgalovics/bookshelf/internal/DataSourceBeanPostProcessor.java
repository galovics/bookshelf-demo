package com.arnoldgalovics.bookshelf.internal;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

public class DataSourceBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        if (bean instanceof DataSource) {
            // @formatter:off
            return ProxyDataSourceBuilder
                    .create((DataSource) bean)
                    .name("dataSource")
                    .countQuery()
                    .build();
            // @formatter:on
        }
        return bean;
    }
}