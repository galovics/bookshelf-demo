package com.arnoldgalovics.bookshelf.service;

import com.arnoldgalovics.bookshelf.service.domain.AuthorView;

import java.util.Collection;

public interface AuthorService {
    Collection<AuthorView> getAuthors();
}
