package org.jcd2052.api.services;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public abstract class BaseService<T> {
    protected final JpaRepository<T, Integer> repository;

    protected BaseService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    public Collection<T> fetchAll() {
        return repository.findAll();
    }

    public Collection<T> fetchEntities(T entity) {
        return repository.findAll(Example.of(entity));
    }

    public void save(T entity) {
        repository.save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }
}