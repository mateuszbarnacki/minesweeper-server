package com.example.ztiproj.config.exception;

import com.example.ztiproj.config.exception.handler.ErrorCode;

public class ExistentUserException extends ZtiProjException {
    public ExistentUserException(String username) {
        super(String.format("User with username <%s> already exists!", username), ErrorCode.BAD_REQUEST);
    }
}
