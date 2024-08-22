package org.jcd2052.api.service.games;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public abstract class BaseService<T> {
    protected final JpaRepository<T, Integer> repository;

    protected BaseService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    public Collection<T> getAll() {
        return repository.findAll();
    }

    public void save(T entity) {
        repository.save(entity);
    }

    @SuppressWarnings("unchecked")
    public T getRepository() {
        return (T) repository;
    }
}