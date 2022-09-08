package com.example.ztiproj.exception.handler;

public enum ErrorCode {
    BAD_REQUEST(400),
    NOT_FOUND(404);

    private final int codeNumber;

    ErrorCode(int codeNumber) {
        this.codeNumber = codeNumber;
    }

    public int getCodeNumber() {
        return codeNumber;
    }
}
