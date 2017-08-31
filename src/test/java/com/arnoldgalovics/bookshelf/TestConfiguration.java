package com.arnoldgalovics.bookshelf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.arnoldgalovics.bookshelf.internal.DataSourceBeanPostProcessor;

@Configuration
public class TestConfiguration {
    @Bean
    public DataSourceBeanPostProcessor dataSourceBeanPostProcessor() {
        return new DataSourceBeanPostProcessor();
    }

    @Bean
    public TestHelper testHelper() {
        return new TestHelper();
    }
}
