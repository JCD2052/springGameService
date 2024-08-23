package org.jcd2052.api.utils.converters;

import jakarta.persistence.AttributeConverter;
import org.jcd2052.api.entities.UserRole;

public class UserRoleConvertor implements AttributeConverter<UserRole, Integer> {
    @Override
    public Integer convertToDatabaseColumn(UserRole userRole) {
        return userRole.ordinal() + 1;
    }

    @Override
    public UserRole convertToEntityAttribute(Integer index) {
        return UserRole.getUserRoleByIndex(index);
    }
}