package com.arnoldgalovics.bookshelf.service;

import com.arnoldgalovics.bookshelf.service.domain.BookView;
import com.arnoldgalovics.bookshelf.service.domain.SimpleBookView;

import java.util.Collection;
import java.util.UUID;

/**
 * Service for Books
 */
public interface BookService {
    /**
     * Returns all the books
     * @return all the books
     */
    Collection<BookView> getBooks();

    /**
     * Returns the book with the corresponding id
     * @param id the id of the book. Should not be null
     * @return the book with the corresponding id
     */
    BookView getBook(UUID id);

    /**
     * Returns the book with the corresponding id in a simplistic format
     * @param id id the id of the book. Should not be null
     * @return the book with the corresponding id in a simplistic format
     * @see SimpleBookView
     */
    SimpleBookView getSimpleBook(UUID id);

    /**
     * Changes the name of the corresponding book.
     * @param id the id of the changed book
     * @param name the new name for the book
     */
    void changeName(UUID id, String name);
}
