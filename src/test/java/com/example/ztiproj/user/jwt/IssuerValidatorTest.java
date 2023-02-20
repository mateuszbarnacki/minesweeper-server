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

import static com.example.ztiproj.user.jwt.JwtFactory.JWT_ISSUER;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-17
 */
@SpringBootTest
class IssuerValidatorTest {
    private final IssuerValidator issuerValidator;
    @Autowired
    private JwtEncoder jwtEncoder;


    IssuerValidatorTest() {
        this.issuerValidator = new IssuerValidator();
    }

    @Test
    void shouldBeValidIssuer() {
        // given
        Jwt jwt = givenValidToken();

        // when
        ValidationResult validationResult = issuerValidator.validate(jwt);

        // then
        Assertions.assertTrue(validationResult.isValid());
    }

    @Test
    void shouldBeInvalidIssuer() {
        // given
        Jwt jwt = givenInvalidToken();

        // when
        ValidationResult validationResult = issuerValidator.validate(jwt);

        // then
        Assertions.assertFalse(validationResult.isValid());
    }

    private Jwt givenValidToken() {
        JwtClaimsSet claims = buildClaimsWithGivenIssuer(JWT_ISSUER);
        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

    private Jwt givenInvalidToken() {
        JwtClaimsSet claims = buildClaimsWithGivenIssuer(Strings.EMPTY);
        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

    private JwtClaimsSet buildClaimsWithGivenIssuer(String issuer) {
        Instant now = Instant.now();
        return JwtClaimsSet.builder()
                .issuer(issuer)
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject("test")
                .claim("scope", Strings.EMPTY)
                .build();
    }
}
