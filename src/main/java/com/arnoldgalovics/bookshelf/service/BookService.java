package com.arnoldgalovics.bookshelf.service;

import com.arnoldgalovics.bookshelf.service.domain.BookView;
import com.arnoldgalovics.bookshelf.service.domain.SimpleBookView;

import java.util.Collection;
import java.util.UUID;

public interface BookService {
    Collection<BookView> getBooks();

    BookView getBook(UUID id);

    SimpleBookView getSimpleBook(UUID id);

    void changeName(UUID id, String name);
}
