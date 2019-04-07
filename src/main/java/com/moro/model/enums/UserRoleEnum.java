package com.moro.model.enums;

public enum UserRoleEnum {
    ADMIN,
    USER;

    public static UserRoleEnum fromString(final String value) {
        try {
            return UserRoleEnum.valueOf(value.toUpperCase());
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format(
                    "Invalid value '%s' for orders given! Has to be "
                            + "either 'user' or 'admin' (case insensitive).", value));
        }
    }
}
