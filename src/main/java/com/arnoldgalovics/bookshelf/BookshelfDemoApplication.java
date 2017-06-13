package com.arnoldgalovics.bookshelf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@ComponentScan(basePackages = {"com.arnoldgalovics.bookshelf.repository.dao", "com.arnoldgalovics.bookshelf.service"})
@SpringBootApplication
public class BookshelfDemoApplication {

    public static void main(final String[] args) {
        SpringApplication.run(BookshelfDemoApplication.class, args);
    }
}