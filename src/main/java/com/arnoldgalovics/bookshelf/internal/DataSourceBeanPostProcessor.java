package com.arnoldgalovics.bookshelf.internal;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import javax.sql.DataSource;

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
                    .listener(new CustomQueryExecutionListener())
                    .countQuery()
                    .build();
            // @formatter:on
        }
        return bean;
    }
}