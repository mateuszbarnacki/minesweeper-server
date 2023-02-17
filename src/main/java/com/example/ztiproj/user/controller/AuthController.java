package com.example.ztiproj.user.controller;

import com.example.ztiproj.user.dto.AuthenticationDto;
import com.example.ztiproj.user.dto.AuthenticationResponseDto;
import com.example.ztiproj.user.dto.CheckTokenDto;
import com.example.ztiproj.user.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authentication")
public class AuthController {
    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;

    public AuthController(TokenService tokenService, AuthenticationManager authenticationManager) {
        this.tokenService = tokenService;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/auth")
    @Operation(hidden = true)
    public AuthenticationResponseDto createToken(@RequestBody AuthenticationDto dto) {
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.username(), dto.password()));
        String token = tokenService.generateToken(authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return new AuthenticationResponseDto(token, authentication.getName());
    }

    @PostMapping("/check-token")
    @Operation(hidden = true)
    public AuthenticationResponseDto checkToken(@RequestBody CheckTokenDto dto) {
        tokenService.checkToken(dto.token());
        return new AuthenticationResponseDto(dto.token(), StringUtils.EMPTY);
    }
}
