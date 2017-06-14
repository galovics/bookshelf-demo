package com.arnoldgalovics.bookshelf.repository.dao;

import com.arnoldgalovics.bookshelf.repository.domain.AuthorEntity;

import java.util.Collection;

/**
 * Repository to query Authors
 */
public interface AuthorRepository {
    /**
     * Returns all the AuthorEntities
     * @return all the AuthorEntities
     */
    Collection<AuthorEntity> findAll();
}
