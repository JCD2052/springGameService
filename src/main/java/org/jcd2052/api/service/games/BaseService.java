package org.jcd2052.api.service.games;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.HashSet;
import java.util.Set;

public abstract class BaseService<T> {
    protected final JpaRepository<T, Integer> repository;

    protected BaseService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    @Fetch(FetchMode.SELECT)
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