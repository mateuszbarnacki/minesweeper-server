package com.example.ztiproj.user.validator;

import java.util.List;

public class ValidationResult {
    private final List<String> errorMessages;

    public ValidationResult(List<String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public boolean isValid() {
        return errorMessages.isEmpty();
    }

    public String getErrorMessage() {
        return String.join("\n", errorMessages);
    }
}
