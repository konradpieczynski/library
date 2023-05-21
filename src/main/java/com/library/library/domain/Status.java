package com.library.library.domain;

public enum Status {
    FREE("free"),
    UNAVAILABLE("unavailable"),
    RENTED("rented"),
    DESTROYED("destroyed");

    private final String value;
    Status(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
