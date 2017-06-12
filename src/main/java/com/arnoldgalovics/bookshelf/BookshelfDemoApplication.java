package com.arnoldgalovics.bookshelf;

import javax.sql.DataSource;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import net.ttddyy.dsproxy.support.ProxyDataSourceBuilder;

@SpringBootApplication
public class BookshelfDemoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BookshelfDemoApplication.class, args);
    }
}

@EnableJpaRepositories
@ComponentScan(basePackages = { "com.arnoldgalovics.bookshelf.repository.dao", "com.arnoldgalovics.bookshelf.service" })
@Configuration
class BookshelfConfiguration {
    @Bean
    public DataSourceBeanPostProcessor dataSourceBeanPostProcessor() {
        return new DataSourceBeanPostProcessor();
    }
}

class DataSourceBeanPostProcessor implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(final Object bean, final String beanName) throws BeansException {
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

    @Override
    public Object postProcessAfterInitialization(final Object bean, final String beanName) throws BeansException {
        return bean;
    }
}