package org.jcd2052.service.games;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseService<T> {
    protected final JpaRepository<T, Integer> repository;

    protected BaseService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    public Set<T> getAll() {
        return new HashSet<>(repository.findAll());
    }

    public void save(T entity) {
        repository.save(entity);
    }

    public void deleteEntity(T entity) {
        repository.delete(entity);
    }

}