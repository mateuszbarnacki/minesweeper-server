package com.example.ztiproj.user.jwt;

import com.example.ztiproj.user.model.User;
import com.example.ztiproj.user.repository.UserRepository;
import com.example.ztiproj.user.validator.ValidationResult;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-17
 */
@SpringBootTest
class SubjectValidatorTest {
    @InjectMocks
    private SubjectValidator subjectValidator;
    @Mock
    private UserRepository userRepository;
    @Autowired
    private JwtEncoder jwtEncoder;

    @Test
    void shouldBeValidSubject() {
        // given
        Jwt jwt = givenValidToken();

        // when
        User user = new User();
        user.setUsername("test");
        Mockito.when(userRepository.findUserByUsername("test")).thenReturn(user);
        ValidationResult validationResult = subjectValidator.validate(jwt);

        // then
        Assertions.assertTrue(validationResult.isValid());
    }

    @Test
    void shouldBeInvalidSubject() {
        // given
        Jwt jwt = givenInvalidToken();

        // when
        Mockito.when(userRepository.findUserByUsername(anyString())).thenReturn(null);
        ValidationResult validationResult = subjectValidator.validate(jwt);

        // then
        Assertions.assertFalse(validationResult.isValid());
    }

    private Jwt givenValidToken() {
        JwtClaimsSet claims = buildClaimsWithGivenIssuer("test");
        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

    private Jwt givenInvalidToken() {
        JwtClaimsSet claims = buildClaimsWithGivenIssuer(Strings.EMPTY);
        return jwtEncoder.encode(JwtEncoderParameters.from(claims));
    }

    private JwtClaimsSet buildClaimsWithGivenIssuer(String subject) {
        Instant now = Instant.now();
        return JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                .subject(subject)
                .claim("scope", Strings.EMPTY)
                .build();
    }
}
