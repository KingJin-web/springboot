package com.king.security.entity;

public enum Role {
    ADMIN(1,"admin"),
    USER(2,"user"),
    END(3, "tmp");


    private final Integer status;
    private final String text;

    Role(Integer status, String text) {
        this.status = status;
        this.text = text;
    }
}
