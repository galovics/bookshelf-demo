package com.arnoldgalovics.bookshelf.service;

import com.arnoldgalovics.bookshelf.TestConfiguration;
import com.arnoldgalovics.bookshelf.TestHelper;
import com.arnoldgalovics.bookshelf.repository.domain.AuthorEntity;
import com.arnoldgalovics.bookshelf.repository.domain.BookEntity;
import com.arnoldgalovics.bookshelf.repository.domain.BookReviewEntity;
import com.arnoldgalovics.bookshelf.repository.domain.LanguageType;
import com.arnoldgalovics.bookshelf.service.domain.BookView;
import com.arnoldgalovics.bookshelf.service.domain.SimpleBookView;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Collection;
import java.util.UUID;

import static com.arnoldgalovics.bookshelf.QueryAssertions.*;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import({TestConfiguration.class})
public class BookServiceImplTest {
    @Autowired
    private TestHelper testHelper;

    @Autowired
    private BookService underTest;

    @After
    public void tearDown() {
        testHelper.resetDB();
    }

    @Test
    public void testGetBooksShouldTriggerOneSelectOnly() {
        // given
        UUID effectiveJavaId = testHelper.doInTransaction(entityManager -> {
            AuthorEntity oneAuthor = new AuthorEntity("Someone..", "There was an author..");
            BookEntity effectiveJava = new BookEntity("Effective Java", 10, "12131231312", LanguageType.ENGLISH, oneAuthor);
            BookReviewEntity review1 = new BookReviewEntity();
            BookReviewEntity review2 = new BookReviewEntity();
            effectiveJava.addReview(review1);
            effectiveJava.addReview(review2);
            BookEntity effectiveJava2 = new BookEntity("Effective Java 2nd edition", 11, "12131231313", LanguageType.ENGLISH, oneAuthor);
            BookEntity cleanCode = new BookEntity("Clean code", 200, "568751213", LanguageType.ENGLISH, oneAuthor);
            entityManager.persist(oneAuthor);
            return effectiveJava.getId();
        });
        // when
        Collection<BookView> result = underTest.getBooks();
        // then
        assertThat(result).hasSize(3);
        assertThat(result.stream().filter(view -> effectiveJavaId.equals(view.getBookId())).findFirst().get().getReviewCount()).isEqualTo(2);
        assertSelectCount(1);
    }

    @Test
    public void testGetBookShouldTriggerOneSelectOnly() {
        // given
        UUID effectiveJavaId = testHelper.doInTransaction(entityManager -> {
            AuthorEntity oneAuthor = new AuthorEntity("Someone..", "There was an author..");
            BookEntity effectiveJava = new BookEntity("Effective Java", 10, "12131231312", LanguageType.ENGLISH, oneAuthor);
            BookReviewEntity review1 = new BookReviewEntity();
            effectiveJava.addReview(review1);
            entityManager.persist(oneAuthor);
            return effectiveJava.getId();
        });
        // when
        BookView result = underTest.getBook(effectiveJavaId);
        // then
        assertThat(result).isNotNull();
        assertThat(result.getReviewCount()).isEqualTo(1);
        assertSelectCount(1);
    }

    @Test
    public void testUpdateNameShouldTriggerOneSelectAndOneUpdateOnly() {
        // given
        UUID effectiveJavaId = testHelper.doInTransaction(entityManager -> {
            AuthorEntity oneAuthor = new AuthorEntity("Someone..", "There was an author..");
            BookEntity effectiveJava = new BookEntity("Effective Java", 10, "12131231312", LanguageType.ENGLISH, oneAuthor);
            entityManager.persist(oneAuthor);
            return effectiveJava.getId();
        });
        // when
        underTest.changeName(effectiveJavaId, "Effective Java 2nd Edition");
        // then
        assertSelectCount(1);
        assertUpdateCount(1);
    }

    @Test
    public void testGetSimpleBookShouldTriggerOneSelectOnly() {
        // given
        UUID effectiveJavaId = testHelper.doInTransaction(entityManager -> {
            AuthorEntity oneAuthor = new AuthorEntity("Someone..", "There was an author..");
            BookEntity effectiveJava = new BookEntity("Effective Java", 10, "12131231312", LanguageType.ENGLISH, oneAuthor);
            entityManager.persist(oneAuthor);
            return effectiveJava.getId();
        });
        // when
        SimpleBookView result = underTest.getSimpleBook(effectiveJavaId);
        // then
        assertThat(result).isNotNull();
        assertSelectCount(1);
        assertSelectionFields("id", "name");
    }
}
