package org.jcd2052.api.services;

import org.jcd2052.api.entities.IEntity;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Collection;

public abstract class BaseService<T extends IEntity> {
    protected final JpaRepository<T, Integer> repository;

    protected BaseService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    public Collection<T> fetchEntities(T probe) {
        return fetchEntities(Example.of(probe));
    }

    public void save(T entity) {
        repository.save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    protected Collection<T> fetchEntities(Example<T> example) {
        return example.getProbe().areObjectFieldsEmpty() ? new ArrayList<>() : repository.findAll(example);
    }
}