package com.arnoldgalovics.bookshelf.service;

import com.arnoldgalovics.bookshelf.service.domain.AuthorView;

import java.util.Collection;

/**
 * Service for Authors
 */
public interface AuthorService {
    /**
     * Returns all the Authors
     * @return all the Authors
     */
    Collection<AuthorView> getAuthors();
}
