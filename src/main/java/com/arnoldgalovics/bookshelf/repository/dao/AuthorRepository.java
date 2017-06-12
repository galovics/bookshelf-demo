package com.arnoldgalovics.bookshelf.repository.dao;

import java.util.Collection;

import com.arnoldgalovics.bookshelf.repository.domain.AuthorEntity;

public interface AuthorRepository {
    Collection<AuthorEntity> findAll();
}
