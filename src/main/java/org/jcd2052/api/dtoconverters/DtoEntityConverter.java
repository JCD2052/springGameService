package org.jcd2052.api.dtoconverters;

import org.jcd2052.api.entities.IEntity;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;

public interface DtoEntityConverter<E extends IEntity, T> {
    T convertToDto(E entity);

    default Comparator<E> createDtoComparator() {
        return Comparator.comparing(IEntity::getId);
    }

    default List<T> createDtoListFromEntities(Collection<E> entities) {
        return entities.stream()
                .sorted(createDtoComparator())
                .map(this::convertToDto)
                .toList();
    }
}
