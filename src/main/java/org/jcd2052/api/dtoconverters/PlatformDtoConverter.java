package org.jcd2052.api.dtoconverters;

import org.jcd2052.api.dto.PlatformDto;
import org.jcd2052.api.entities.Platform;
import org.springframework.stereotype.Component;

@Component
public class PlatformDtoConverter implements DtoEntityConverter<Platform, PlatformDto> {
    @Override
    public PlatformDto convertToDto(Platform entity) {
        return PlatformDto.builder()
                .id(entity.getId())
                .platformName(entity.getPlatformName())
                .build();
    }
}
