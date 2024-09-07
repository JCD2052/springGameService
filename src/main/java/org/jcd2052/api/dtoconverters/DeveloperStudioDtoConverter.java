package org.jcd2052.api.dtoconverters;

import org.jcd2052.api.dto.DeveloperStudioDto;
import org.jcd2052.api.entities.DeveloperStudio;
import org.springframework.stereotype.Component;

@Component
public class DeveloperStudioDtoConverter implements DtoEntityConverter<DeveloperStudio, DeveloperStudioDto> {
    @Override
    public DeveloperStudioDto convertToDto(DeveloperStudio entity) {
        return DeveloperStudioDto.builder()
                .id(entity.getId())
                .developerStudioName(entity.getStudioName())
                .build();
    }
}
