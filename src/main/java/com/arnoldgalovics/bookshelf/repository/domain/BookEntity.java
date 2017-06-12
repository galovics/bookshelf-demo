package com.arnoldgalovics.bookshelf.repository.domain;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    private UUID id;

    @Column(name = "name")
    private String name;

    @Column(name = "page_count")
    private Integer pageCount;

    @Column(name = "isbn")
    private String isbn;

    @Column(name = "language")
    @Enumerated(EnumType.STRING)
    private LanguageType language;

    @ManyToOne(fetch = FetchType.LAZY)
    private AuthorEntity author;

    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.LAZY, mappedBy = "book")
    private Set<BookReviewEntity> reviews = new HashSet<>();

    BookEntity() {
    }

    public BookEntity(final String name, final int pageCount, final String isbn, final LanguageType language, final AuthorEntity author) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.pageCount = pageCount;
        this.isbn = isbn;
        this.language = language;
        this.author = author;
        this.author.addBook(this);
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public Integer getPageCount() {
        return pageCount;
    }

    public String getIsbn() {
        return isbn;
    }

    public LanguageType getLanguage() {
        return language;
    }

    public void setPageCount(final Integer pageCount) {
        this.pageCount = pageCount;
    }

    public void setIsbn(final String isbn) {
        this.isbn = isbn;
    }

    public void setLanguage(final LanguageType language) {
        this.language = language;
    }

    void setAuthor(final AuthorEntity author) {
        this.author = author;
    }

    public AuthorEntity getAuthor() {
        return author;
    }

    public void addReview(final BookReviewEntity review) {
        reviews.add(review);
    }

    public void removeReview(final BookReviewEntity review) {
        reviews.remove(review);
    }

    public Set<BookReviewEntity> getReviews() {
        return Collections.unmodifiableSet(reviews);
    }

    public int getReviewCount() {
        return reviews.size();
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
        BookEntity other = (BookEntity) obj;
        return new EqualsBuilder().append(id, other.id).isEquals();
    }

}
