package com.arnoldgalovics.bookshelf.service;

import java.util.Collection;
import java.util.UUID;

import com.arnoldgalovics.bookshelf.service.domain.BookView;

public interface BookService {
    Collection<BookView> getBooks();

    BookView getBook(UUID id);

    void changeName(UUID id, String name);
}
