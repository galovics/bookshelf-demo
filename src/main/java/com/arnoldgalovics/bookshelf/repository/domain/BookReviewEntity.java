package com.arnoldgalovics.bookshelf.repository.domain;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

@Entity
@Table(name = "book_reviews")
public class BookReviewEntity {
    @Id
    private UUID id;

    @ManyToOne
    private BookEntity book;

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
        BookReviewEntity other = (BookReviewEntity) obj;
        return new EqualsBuilder().append(id, other.id).isEquals();
    }
}
