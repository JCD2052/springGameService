package org.jcd2052.api.factories;

import org.jcd2052.api.dto.PlatformDto;
import org.jcd2052.api.entities.Platform;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class PlatformDtoConverter implements DtoEntityConverter<Platform, PlatformDto> {
    @Override
    public PlatformDto convertToDto(Platform entity) {
        return PlatformDto.builder()
                .id(entity.getId())
                .platformName(entity.getPlatformName())
                .build();
    }

    @Override
    public Comparator<PlatformDto> createDtoComparator() {
        return Comparator.comparing(PlatformDto::getId);
    }
}
