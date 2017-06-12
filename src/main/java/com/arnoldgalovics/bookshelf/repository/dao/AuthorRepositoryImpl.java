package com.arnoldgalovics.bookshelf.repository.dao;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.arnoldgalovics.bookshelf.repository.domain.AuthorEntity;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<AuthorEntity> findAll() {
        return entityManager.createQuery("FROM AuthorEntity", AuthorEntity.class).getResultList();
    }
}
