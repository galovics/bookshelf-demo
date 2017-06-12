package com.arnoldgalovics.bookshelf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {
    @Bean
    public TestHelper testHelper() {
        return new TestHelper();
    }
}
