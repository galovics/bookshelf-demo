package com.arnoldgalovics.bookshelf.repository.dao;

import com.arnoldgalovics.bookshelf.repository.domain.AuthorEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Collection;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<AuthorEntity> findAll() {
        return entityManager.createQuery("FROM AuthorEntity", AuthorEntity.class).getResultList();
    }
}
