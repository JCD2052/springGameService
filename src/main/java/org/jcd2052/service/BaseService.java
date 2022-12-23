package org.jcd2052.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseService<T> {
    private final JpaRepository<T, Integer> repository;

    protected BaseService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    public Set<T> getAll() {
        return new HashSet<>(repository.findAll());
    }

    public T getById(int id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Couldn't find entity with id " + id));
    }

    public void save(T entity) {
        repository.save(entity);
    }

    public void updateById(int id, T entityWithUpdates) {
        T entityToBeUpdated = getById(id);
        updateById(entityToBeUpdated, entityWithUpdates);
        repository.save(entityToBeUpdated);
    }

    public void deleteById(int id) {
        repository.deleteById(id);
    }

    protected abstract void updateById(T entityToBeUpdated, T entityWithUpdates);
}