package com.company.entity;

import org.apache.commons.lang3.StringUtils;

public enum Role {
    USER(10, "USER");

    private final int code;
    private final String name;

    Role(final int code, final String name) {
        this.code = code;
        this.name = name;
    }

    private int getCode() {
        return code;
    }

    private String getName() {
        return name;
    }

    public static Role fromName(String name) {
        if (StringUtils.isBlank(name)) {
            return null;
        }
        if (name.equalsIgnoreCase(USER.name)) {
            return USER;
        } else {
            return null;
        }
    }

    public static Role fromCode(Integer code) {
        if (code == null) {
            return null;
        }

        if (code == USER.code) {
            return USER;
        } else {
            return null;
        }
    }
}