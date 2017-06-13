package com.arnoldgalovics.bookshelf.repository.dao;

import com.arnoldgalovics.bookshelf.repository.domain.BookEntity;

import java.util.Collection;
import java.util.UUID;

public interface BookRepository {
    Collection<BookEntity> findAll();

    BookEntity findById(UUID id);
}
