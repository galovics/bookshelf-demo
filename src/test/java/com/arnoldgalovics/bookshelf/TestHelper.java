package com.arnoldgalovics.bookshelf;

import com.arnoldgalovics.bookshelf.internal.QueryStatementHolder;
import com.arnoldgalovics.bookshelf.repository.domain.AuthorEntity;
import com.arnoldgalovics.bookshelf.repository.domain.BookEntity;
import com.arnoldgalovics.bookshelf.repository.domain.BookReviewEntity;
import net.ttddyy.dsproxy.QueryCountHolder;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import java.util.function.Consumer;
import java.util.function.Function;

@Transactional
public class TestHelper {
    @PersistenceContext
    private EntityManager entityManager;

    public void doInTransaction(final Consumer<EntityManager> consumer) {
        consumer.accept(entityManager);
    }

    public <T> T doInTransaction(final Function<EntityManager, T> function) {
        return function.apply(entityManager);
    }

    public void resetDB() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaDelete<AuthorEntity> authorDelete = cb.createCriteriaDelete(AuthorEntity.class);
        authorDelete.from(AuthorEntity.class);
        CriteriaDelete<BookEntity> bookDelete = cb.createCriteriaDelete(BookEntity.class);
        bookDelete.from(BookEntity.class);
        CriteriaDelete<BookReviewEntity> reviewDelete = cb.createCriteriaDelete(BookReviewEntity.class);
        reviewDelete.from(BookReviewEntity.class);
        entityManager.createQuery(reviewDelete).executeUpdate();
        entityManager.createQuery(bookDelete).executeUpdate();
        entityManager.createQuery(authorDelete).executeUpdate();
        QueryCountHolder.clear();
        QueryStatementHolder.clear();
    }

}
