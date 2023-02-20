package com.example.ztiproj.user.jwt;

import com.example.ztiproj.user.validator.ValidationResult;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-17
 */
@SpringBootTest
class ExpirationTimeValidatorTest {
    private final ExpirationTimeValidator expirationTimeValidator;
    @Autowired
    private JwtEncoder jwtEncoder;

    ExpirationTimeValidatorTest() {
        this.expirationTimeValidator = new ExpirationTimeValidator();
    }

    @Test
    void shouldBeValidExpirationTime() {
        // given
        Jwt jwt = givenValidToken();

        // when
        ValidationResult validationResult = expirationTimeValidator.validate(jwt);

        // then
        Assertions.assertTrue(validationResult.isValid());

    }

    @Test
    void shouldBeInvalidExpirationTime() {
        // given
        Jwt jwt = givenInvalidToken();

        // when
        ValidationResult validationResult = expirationTimeValidator.validate(jwt);

        // then
        Assertions.assertFalse(validationResult.isValid());
    }

    private Jwt givenValidToken() {
        Instant now = Instant.now();
        JwtClaimsSet claims = buildClaimsWithGivenIssuer(now);
        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

    private Jwt givenInvalidToken() {
        Instant yesterday = Instant.now().minus(1, ChronoUnit.DAYS);
        JwtClaimsSet claims = buildClaimsWithGivenIssuer(yesterday);
        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

    private JwtClaimsSet buildClaimsWithGivenIssuer(Instant now) {
        return JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject("test")
                .claim("scope", Strings.EMPTY)
                .build();
    }
}
