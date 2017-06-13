package com.arnoldgalovics.bookshelf.service.domain;

import com.arnoldgalovics.bookshelf.repository.domain.BookEntity;
import com.arnoldgalovics.bookshelf.repository.domain.LanguageType;

import java.util.UUID;

public class BookView {
    private UUID bookId;
    private int pageCount;
    private String isbn;
    private LanguageType language;
    private int reviewCount;

    public BookView(final BookEntity entity) {
        this.bookId = entity.getId();
        this.pageCount = entity.getPageCount();
        this.isbn = entity.getIsbn();
        this.language = entity.getLanguage();
        this.reviewCount = entity.getReviewCount();
    }

    public UUID getBookId() {
        return bookId;
    }

    public int getPageCount() {
        return pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public LanguageType getLanguage() {
        return language;
    }

    public int getReviewCount() {
        return reviewCount;
    }
}
