package org.jcd2052.api.factories;

import org.jcd2052.api.dto.DeveloperStudioDto;
import org.jcd2052.api.entities.DeveloperStudio;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class DeveloperStudioDtoConverter implements DtoEntityConverter<DeveloperStudio, DeveloperStudioDto> {
    @Override
    public DeveloperStudioDto convertToDto(DeveloperStudio entity) {
        return DeveloperStudioDto.builder()
                .id(entity.getId())
                .developerStudioName(entity.getStudioName())
                .build();
    }

    @Override
    public Comparator<DeveloperStudioDto> createDtoComparator() {
        return Comparator.comparing(DeveloperStudioDto::getId);
    }
}
