package com.example.ztiproj.user.jwt;

import com.example.ztiproj.user.AuthRole;
import com.example.ztiproj.user.model.User;
import com.example.ztiproj.user.repository.UserRepository;
import com.example.ztiproj.user.validator.ValidationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.Jwt;

import static org.mockito.ArgumentMatchers.anyString;

/**
 * @author Mateusz Barnacki
 * @version 2.0
 * @since 2023-02-17
 */
@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TokenValidatorTest {
    @Autowired
    private TokenValidator tokenValidator;
    @MockBean
    private UserRepository userRepository;
    @Autowired
    private JwtFactory jwtFactory;

    @Test
    void givenTokenShouldBeValid() {
        // given
        User user = new User();
        user.setUsername("test");
        Mockito.when(userRepository.findUserByUsername("test")).thenReturn(user);
        Jwt jwt = givenTokenWithCorrectCredentials();

        // when
        ValidationResult validationResult = tokenValidator.validate(jwt);

        // then
        Mockito.verify(userRepository).findUserByUsername(anyString());
        Assertions.assertTrue(validationResult.isValid());
    }

    @Test
    void givenTokenWithNonExistentUserShouldBeInvalid() {
        // given
        Mockito.when(userRepository.findUserByUsername("test2")).thenReturn(null);
        Jwt jwt = givenTokenWithNonExistentUser();

        // when
        ValidationResult validationResult = tokenValidator.validate(jwt);

        // then
        Mockito.verify(userRepository).findUserByUsername(anyString());
        Assertions.assertFalse(validationResult.isValid());
        Assertions.assertEquals("Invalid subject!", validationResult.getErrorMessage());
    }

    private Jwt givenTokenWithCorrectCredentials() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("test", AuthRole.USER);
        return jwtFactory.generate(authentication);
    }

    private Jwt givenTokenWithNonExistentUser() {
        Authentication authentication = new UsernamePasswordAuthenticationToken("test2", AuthRole.USER);
        return jwtFactory.generate(authentication);
    }
}
