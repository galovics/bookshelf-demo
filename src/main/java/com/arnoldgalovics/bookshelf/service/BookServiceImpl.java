package com.arnoldgalovics.bookshelf.service;

import static java.util.stream.Collectors.toList;

import java.util.Collection;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.arnoldgalovics.bookshelf.repository.dao.BookRepository;
import com.arnoldgalovics.bookshelf.repository.domain.BookEntity;
import com.arnoldgalovics.bookshelf.service.domain.BookView;
import com.arnoldgalovics.bookshelf.service.domain.SimpleBookView;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookServiceHelper helper;

    @Override
    @Transactional
    public Collection<BookView> getBooks() {
        return bookRepository.findAll().stream().map(BookView::new).collect(toList());
    }

    @Override
    @Transactional
    public BookView getBook(final UUID id) {
        return new BookView(bookRepository.findById(id));
    }

    @Override
    @Transactional
    public SimpleBookView getSimpleBook(final UUID id) {
        return bookRepository.findViewById(id);
    }

    @Override
    @javax.transaction.Transactional
    public void changeName(final UUID id, final String name) {
        final BookEntity book = bookRepository.findById(id);
        helper.updateName(book, name);
    }

}