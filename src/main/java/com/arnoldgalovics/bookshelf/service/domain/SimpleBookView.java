package com.arnoldgalovics.bookshelf.service.domain;

import java.util.UUID;

public class SimpleBookView {
    private final UUID bookId;
    private final String name;
	
    public SimpleBookView(final UUID bookId, final String name) {
		this.bookId = bookId;
		this.name = name;
	}
    
}
