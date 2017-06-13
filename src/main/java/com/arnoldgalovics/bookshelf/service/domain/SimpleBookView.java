package com.arnoldgalovics.bookshelf.service.domain;

import com.arnoldgalovics.bookshelf.repository.domain.BookEntity;

import java.util.UUID;

public class SimpleBookView {
    private UUID bookId;
    private String name;

    public SimpleBookView(BookEntity entity) {
        this.bookId = entity.getId();
        this.name = entity.getName();
    }
}
