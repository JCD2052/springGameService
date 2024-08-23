package org.jcd2052.api.entities;

public enum UserRole {
    ADMIN, COMMON_USER;

    public static UserRole getUserRoleByIndex(int index) {
        return UserRole.values()[index - 1];
    }
}
