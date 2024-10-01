package org.jcd2052.api.entities;

import java.util.Arrays;
import java.util.Optional;

public interface IEntity {
    Long getId();

    default boolean areObjectFieldsEmpty() {
        return Arrays.stream(this.getClass().getDeclaredFields()).allMatch(field -> {
            field.setAccessible(true);
            try {
                boolean isFieldValueNull = Optional.ofNullable(field.get(this)).isEmpty();
                field.setAccessible(false);
                return isFieldValueNull;
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
