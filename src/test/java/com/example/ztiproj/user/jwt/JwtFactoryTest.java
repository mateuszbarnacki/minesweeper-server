package com.example.ztiproj.user.jwt;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-17
 */
@SpringBootTest
class JwtFactoryTest {
    @Autowired
    private JwtFactory jwtFactory;

    @Test
    void shouldGenerateToken() {
        // given
        Authentication authentication = new UsernamePasswordAuthenticationToken("test", "Test");

        // when
        Jwt jwt = jwtFactory.generate(authentication);

        // then
        String token = jwt.getTokenValue();
        Assertions.assertFalse(token.isEmpty());
    }
}
