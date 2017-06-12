package com.arnoldgalovics.bookshelf.service;

import static com.arnoldgalovics.bookshelf.QueryAssertions.assertSelectCount;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.arnoldgalovics.bookshelf.TestConfiguration;
import com.arnoldgalovics.bookshelf.TestHelper;
import com.arnoldgalovics.bookshelf.repository.domain.AuthorEntity;
import com.arnoldgalovics.bookshelf.service.domain.AuthorView;

@SpringBootTest
@RunWith(SpringRunner.class)
@Import({ TestConfiguration.class })
public class AuthorServiceImplTest {
    @Autowired
    private TestHelper testHelper;

    @Autowired
    private AuthorService underTest;

    @Test
    public void testGetAuthorsShouldOnlyTriggerOneSelect() {
        // given
        testHelper.doInTransaction(entityManager -> {
            AuthorEntity joshua = new AuthorEntity("Joshua Bloch", "I'm the god of Java :-)");
            entityManager.persist(joshua);
        });
        // when
        Collection<AuthorView> result = underTest.getAuthors();
        // then
        assertThat(result).hasSize(1);
        assertSelectCount(1);
    }
}
