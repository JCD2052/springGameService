package org.jcd2052.api.factories;

import org.jcd2052.api.dto.UserDto;
import org.jcd2052.api.entities.User;
import org.springframework.stereotype.Component;

import java.util.Comparator;

@Component
public class UserDtoConverter implements DtoEntityConverter<User, UserDto> {
    public UserDto convertToDto(User entity) {
        return UserDto.builder()
                .id(entity.getId())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .userRole(entity.getUserRole().name())
                .timeCreated(entity.getTimeCreated())
                .build();
    }

    @Override
    public Comparator<UserDto> createDtoComparator() {
        return Comparator.comparing(UserDto::getId);
    }
}
