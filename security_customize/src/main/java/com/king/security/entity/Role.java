package com.king.security.entity;

public enum Role {
    //这里为啥要加 ROLE_ ?
    ADMIN(1, "ROLE_ADMIN"),
    USER(2, "ROLE_USER"),
    TEMP(3, "ROLE_TEMP");


    private final Integer status;
    private final String text;

    Role(Integer status, String text) {
        this.status = status;
        this.text = text;
    }


    public Integer getStatus() {
        return status;
    }

    public String getText() {
        return text;
    }
}
