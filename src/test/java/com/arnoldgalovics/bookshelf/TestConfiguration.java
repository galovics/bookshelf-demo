package com.arnoldgalovics.bookshelf;

import com.arnoldgalovics.bookshelf.internal.DataSourceBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
