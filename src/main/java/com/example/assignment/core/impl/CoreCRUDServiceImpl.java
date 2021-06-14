package com.example.assignment.core.impl;

import com.example.assignment.core.CoreCRUDService;
import com.example.assignment.core.entity.CoreEntity;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.List;

public abstract class CoreCRUDServiceImpl<T extends CoreEntity> implements CoreCRUDService<T> {

    @Autowired
    protected EntityManager entityManager;

    public CoreCRUDServiceImpl() {
    }

    @Override
    public List<T> findAll() {
        return entityManager.createQuery("SELECT a FROM " + getManagedClass().getSimpleName() + " a", getManagedClass()).getResultList();
    }

    @Override
    public T create(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public boolean deleteById(Long id) {
        T authorEntity = findById(id);
        if (authorEntity == null) {
            return false;
        }
        entityManager.remove(authorEntity);
        return true;
    }

    @Override
    public T update(T entity) {
        T updatebleBook = findById(entity.getId());
        if (updatebleBook != null) {
            updateCore(updatebleBook, entity);
            entityManager.merge(updatebleBook);
        }
        return updatebleBook;
    }

    @Override
    public T findById(Long id) {
        // dobhat az eclipseLink: NoResultException
        return entityManager.find(getManagedClass(), id);
    }

    protected abstract void updateCore(T updatableEntity, T entity);

    protected abstract Class<T> getManagedClass();
}
