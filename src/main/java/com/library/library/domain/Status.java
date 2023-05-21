package com.library.library.domain;

public enum Status {
    FREE("free"),
    BORROWED("borrowed"),
    DESTROYED("destroyed");

    private final String value;
    Status(String value) {
        this.value = value;
    }
    public String getValue() {
        return value;
    }
}
