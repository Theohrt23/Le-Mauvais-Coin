package com.epitech.lemauvaiscoin.domain;

public enum UserRole {

    USER(Values.USER), COMPANY(Values.COMPANY), ADMIN(Values.ADMIN);

    UserRole (String val) {
        if (!this.name().equals(val))
            throw new IllegalArgumentException("Incorrect use of UserRole");
    }

    public static class Values {
        public static final String USER = "ROLE_USER";
        public static final String COMPANY = "ROLE_COMPANY";
        public static final String ADMIN = "ROLE_ADMIN";
    }
}
