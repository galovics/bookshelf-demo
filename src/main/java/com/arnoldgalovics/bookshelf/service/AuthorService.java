package com.arnoldgalovics.bookshelf.service;

import java.util.Collection;

import com.arnoldgalovics.bookshelf.service.domain.AuthorView;

public interface AuthorService {
    Collection<AuthorView> getAuthors();
}
