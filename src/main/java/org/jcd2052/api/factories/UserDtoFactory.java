package org.jcd2052.api.factories;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jcd2052.api.dto.UserDto;
import org.jcd2052.api.entities.User;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserDtoFactory {
    public static List<UserDto> createUserDtoList(Collection<User> users) {
        return users.stream().map(User::toUserDto).toList();
    }
}
