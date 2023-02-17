package com.example.ztiproj.user.jwt;

import com.example.ztiproj.user.model.User;
import com.example.ztiproj.user.repository.UserRepository;
import com.example.ztiproj.user.validator.JwtValidator;
import com.example.ztiproj.user.validator.ValidationResult;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.example.ztiproj.user.jwt.JwtFactory.JWT_ISSUER;

@Component
public class TokenValidator {
    private final List<JwtValidator> validators;
    @Autowired
    private JwtDecoder decoder;
    @Autowired
    private UserRepository userRepository;

    public TokenValidator() {
        this.validators = List.of(
                new IssuerValidator(),
                new SubjectValidator(userRepository),
                new ExpirationTimeValidator());
    }

    public TokenValidator(List<JwtValidator> validators) {
        this.validators = validators;
    }

    public ValidationResult validate(String token) {
        Jwt jwt = decoder.decode(token);
        return new ValidationResult(validators.stream()
                .map(jwtValidator -> jwtValidator.validate(jwt))
                .map(ValidationResult::getErrorMessage)
                .filter(Strings::isNotBlank)
                .toList());
    }
}

class IssuerValidator implements JwtValidator {
    @Override
    public ValidationResult validate(Jwt jwt) {
        String issuer = jwt.getIssuer().toString();
        return buildValidationResult(issuer);
    }

    private ValidationResult buildValidationResult(String issuer) {
        ValidationResult validationResult = new ValidationResult(Collections.emptyList());
        if (issuer.equals(JWT_ISSUER)) {
            validationResult = new ValidationResult(List.of("Invalid issuer!"));
        }
        return validationResult;
    }
}

class SubjectValidator implements JwtValidator {
    private final UserRepository userRepository;

    public SubjectValidator(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public ValidationResult validate(Jwt jwt) {
        String username = jwt.getSubject();
        return buildValidationResult(username);
    }

    private ValidationResult buildValidationResult(String username) {
        User user = userRepository.findUserByUsername(username);
        return Optional.ofNullable(user)
                .map(User::getUsername)
                .filter(Strings::isNotBlank)
                .map(message -> new ValidationResult(Collections.emptyList()))
                .orElse(new ValidationResult(List.of("Invalid subject!")));
    }
}

class ExpirationTimeValidator implements JwtValidator {
    @Override
    public ValidationResult validate(Jwt jwt) {
        Instant expires = jwt.getExpiresAt();
        return buildValidationResults(expires);
    }

    private ValidationResult buildValidationResults(Instant expires) {
        ValidationResult validationResult = new ValidationResult(Collections.emptyList());
        if (isTokenExpired(expires)) {
            validationResult = new ValidationResult(List.of("Token already expired!"));
        }
        return validationResult;
    }

    private boolean isTokenExpired(Instant expires) {
        return expires.compareTo(Instant.now()) >= 0;
    }
}
