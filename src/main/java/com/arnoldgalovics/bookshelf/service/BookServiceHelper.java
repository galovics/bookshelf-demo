package com.arnoldgalovics.bookshelf.service;

import com.arnoldgalovics.bookshelf.repository.domain.BookEntity;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class BookServiceHelper {
    @PersistenceContext
    private EntityManager entityManager;

    public BookEntity updateName(final BookEntity book, final String newName) {
        book.setName(newName);
        return entityManager.merge(book);
    }
}
