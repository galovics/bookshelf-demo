package com.arnoldgalovics.bookshelf.repository.dao;

import com.arnoldgalovics.bookshelf.repository.domain.AuthorEntity;

import java.util.Collection;

public interface AuthorRepository {
    Collection<AuthorEntity> findAll();
}
