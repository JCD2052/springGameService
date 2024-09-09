package org.jcd2052.api.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.constants.JwtConstants;
import org.jcd2052.api.dto.AuthTokenDto;
import org.jcd2052.api.dto.AuthDtoInput;
import org.jcd2052.api.jwt.JwtService;
import org.jcd2052.api.services.UserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @PostMapping(consumes = ApiConstants.APPLICATION_CONTENT_TYPE, produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    public ResponseEntity<AuthTokenDto> performAuth(@RequestBody AuthDtoInput input) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                input.getUsername(),
                input.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(input.getUsername());

        String token = jwtService.generateToken(userDetails);
        AuthTokenDto authTokenDto = AuthTokenDto.builder()
                .token(String.join(StringUtils.SPACE, JwtConstants.BEARER, token))
                .expiresIn(jwtService.getExpirationTime())
                .build();
        return ResponseEntity.ok(authTokenDto);
    }
}
