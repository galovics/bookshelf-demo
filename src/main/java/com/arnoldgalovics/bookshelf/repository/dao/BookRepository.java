package com.arnoldgalovics.bookshelf.repository.dao;

import java.util.Collection;
import java.util.UUID;

import com.arnoldgalovics.bookshelf.repository.domain.BookEntity;
import com.arnoldgalovics.bookshelf.service.domain.SimpleBookView;

/**
 * Repository to query Books
 */
public interface BookRepository {
    /**
     * Returns all the BookEntities
     * @return all the BookEntities
     */
    Collection<BookEntity> findAll();

    /**
     * Returns the BookEntity with the corresponding id
     * @param id the id of the searched BookEntity. Should be not null.
     * @return the BookEntity with the corresponding id
     */
    BookEntity findById(UUID id);

	SimpleBookView findViewById(UUID id);
}
