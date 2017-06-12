package com.arnoldgalovics.bookshelf.service.domain;

import java.util.UUID;

import com.arnoldgalovics.bookshelf.repository.domain.AuthorEntity;

public class AuthorView {
    private UUID authorId;
    private String name;
    private String introduction;

    public AuthorView(final AuthorEntity entity) {
        this.authorId = entity.getId();
        this.name = entity.getName();
        this.introduction = entity.getIntroduction();
    }

    public UUID getAuthorId() {
        return authorId;
    }

    public String getName() {
        return name;
    }

    public String getIntroduction() {
        return introduction;
    }
}
