package com.arnoldgalovics.bookshelf.repository.dao;

import java.util.Collection;
import java.util.UUID;

import com.arnoldgalovics.bookshelf.repository.domain.BookEntity;

public interface BookRepository {
    Collection<BookEntity> findAll();

    BookEntity findById(UUID id);
}
