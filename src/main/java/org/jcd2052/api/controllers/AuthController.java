package org.jcd2052.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.constants.JwtConstants;
import org.jcd2052.api.dto.output.AuthTokenDto;
import org.jcd2052.api.dto.input.AuthDtoInput;
import org.jcd2052.api.jwt.JwtService;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.jcd2052.api.services.UserDetailsService;
import org.springframework.http.HttpStatus;
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
@Tag(name = "Auth")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Operation(summary = "Authenticate user")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    content = {@Content(schema = @Schema(name = "Authentication response",
                            implementation = AuthTokenDto.class),
                            mediaType = ApiConstants.APPLICATION_CONTENT_TYPE)}),
            @ApiResponse(
                    description = "Response when authentication failed",
                    responseCode = "404",
                    content = {@Content(mediaType = ApiConstants.APPLICATION_CONTENT_TYPE)})})
    @PostMapping(consumes = ApiConstants.APPLICATION_CONTENT_TYPE, produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    public ResponseEntity<BaseResponse> performAuth(@RequestBody AuthDtoInput input) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                input.getUsername(),
                input.getPassword()));
        UserDetails userDetails = userDetailsService.loadUserByUsername(input.getUsername());

        String token = jwtService.generateToken(userDetails);
        AuthTokenDto authTokenDto = AuthTokenDto.builder()
                .token(String.join(StringUtils.SPACE, JwtConstants.BEARER, token))
                .expiresIn(jwtService.getExpirationTime())
                .build();
        return ResponseFactory.createResponse(authTokenDto, HttpStatus.OK);
    }
}
