package org.jcd2052.api.factories;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface DtoEntityConverter<E, T> {
    T convertToDto(E entity);

    Comparator<T> createDtoComparator();

    default List<T> createDtoListFromEntities(Collection<E> entities) {
        return entities.stream()
                .map(this::convertToDto)
                .sorted(createDtoComparator())
                .toList();
    }
}
