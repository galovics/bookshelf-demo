package com.arnoldgalovics.bookshelf.repository.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "authors")
public class AuthorEntity {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "introduction")
    private String introduction;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "author")
    private final Set<BookEntity> books = new HashSet<>();

    AuthorEntity() {
    }

    public AuthorEntity(final String name, final String introduction) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.introduction = introduction;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public Set<BookEntity> getBooks() {
        return Collections.unmodifiableSet(books);
    }

    public void removeBook(final BookEntity book) {
        books.remove(book);
        book.setAuthor(null);
    }

    public void addBook(final BookEntity book) {
        books.add(book);
        book.setAuthor(this);
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder().append(id).toHashCode();
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final AuthorEntity other = (AuthorEntity) obj;
        return new EqualsBuilder().append(id, other.id).isEquals();
    }
}
