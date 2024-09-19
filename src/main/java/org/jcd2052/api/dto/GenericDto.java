package org.jcd2052.api.dto;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

public abstract class GenericDto {
    @SneakyThrows
    @Override
    public String toString() {
        return new ObjectMapper().writeValueAsString(this.getClass());
    }
}
