package com.example.ztiproj.user.validator;

import java.util.List;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-17
 */
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
