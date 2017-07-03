package com.arnoldgalovics.bookshelf.repository.dao;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.arnoldgalovics.bookshelf.repository.domain.BookEntity;
import com.arnoldgalovics.bookshelf.service.domain.SimpleBookView;

@Repository
public class BookRepositoryImpl implements BookRepository {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Collection<BookEntity> findAll() {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<BookEntity> query = cb.createQuery(BookEntity.class);
        final Root<BookEntity> root = query.from(BookEntity.class);
        root.fetch("reviews", JoinType.LEFT);
        return new HashSet<>(entityManager.createQuery(query).getResultList());
    }

    @Override
    @Transactional
    public BookEntity findById(final UUID id) {
        final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<BookEntity> query = cb.createQuery(BookEntity.class);
        final Root<BookEntity> root = query.from(BookEntity.class);
        root.fetch("reviews", JoinType.LEFT);
        query.where(cb.equal(root.get("id"), id));
        return entityManager.createQuery(query).getSingleResult();
    }
    
    @Override
    public SimpleBookView findViewById(final UUID id) {
    	final CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        final CriteriaQuery<SimpleBookView> query = cb.createQuery(SimpleBookView.class);
        final Root<BookEntity> root = query.from(BookEntity.class);
        query.multiselect(root.get("id"), root.get("name"));
        query.where(cb.equal(root.get("id"), id));
        return entityManager.createQuery(query).getSingleResult();
    }
}
