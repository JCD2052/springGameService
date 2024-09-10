package org.jcd2052.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.entities.User;
import org.jcd2052.api.dto.input.UserDtoInput;
import org.jcd2052.api.dtoconverters.UserDtoConverter;
import org.jcd2052.api.entities.UserDetails;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.exceptionhandler.exceptions.UserAlreadyCreatedException;
import org.jcd2052.api.services.UserService;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
@Tag(name = "Users")
public class UsersController {
    private final UserService userService;
    private final UserDtoConverter userDtoConverter;
    private final PasswordEncoder passwordEncoder;

    @Operation(summary = "Fetch users records")
    @GetMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    public ResponseEntity<BaseResponse> fetchUsers(
            @RequestParam(required = false) Integer userId,
            @RequestParam(required = false) String userName,
            @RequestParam(required = false) String email,
            @RequestParam(required = false) String userRole) {
        User userProbe = User.createUser(userId, userName, email, userRole);
        return ResponseFactory.createResponse(
                userDtoConverter.createDtoListFromEntities(userService.fetchEntities(userProbe)),
                HttpStatus.OK);
    }

    @Operation(summary = "Fetch the certain user record")
    @GetMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE, value = "/{id}")
    public ResponseEntity<BaseResponse> getUserById(@PathVariable("id") int userId) {
        return ResponseFactory.createResponse(
                userDtoConverter.convertToDto(userService.findByIdOrThrowError(userId)),
                HttpStatus.OK);
    }

    @Operation(summary = "Fetch the authenticated user record")
    @GetMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE, value = "/me")
    public ResponseEntity<BaseResponse> getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails authenticatedUser = (UserDetails) authentication.getPrincipal();
        return ResponseFactory.createResponse(
                userDtoConverter.convertToDto(userService.findUserByNameOrEmailOrThrowError(
                        authenticatedUser.getUsername(),
                        StringUtils.EMPTY)),
                HttpStatus.OK);
    }

    @Operation(summary = "Create a user record")
    @PostMapping(consumes = ApiConstants.APPLICATION_CONTENT_TYPE, produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    @Transactional
    public ResponseEntity<BaseResponse> createUser(@RequestBody UserDtoInput input) {
        String username = input.getUsername();
        String email = input.getEmail();

        if (!userService.isUserExistedByNameOrEmail(username, email)) {
            User user = User.builder()
                    .username(username)
                    .password(passwordEncoder.encode(input.getPassword()))
                    .email(email)
                    .userRole(input.getRoleName())
                    .build();
            userService.save(user);
            return ResponseFactory.createResponse(userDtoConverter.convertToDto(user), HttpStatus.CREATED);
        }
        throw new UserAlreadyCreatedException(username, email);
    }

    @Operation(summary = "Update the certain user record")
    @Transactional
    @PutMapping(
            consumes = ApiConstants.APPLICATION_CONTENT_TYPE,
            produces = ApiConstants.APPLICATION_CONTENT_TYPE,
            value = "/{userId}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable int userId, @RequestBody UserDtoInput input) {
        String username = Optional.ofNullable(input.getUsername()).orElse(StringUtils.EMPTY);
        String email = Optional.ofNullable(input.getEmail()).orElse(StringUtils.EMPTY);

        if (userService.isUserExistedByNameOrEmail(username, email)) {
            User user = userService.findByIdOrThrowError(userId);
            Optional.ofNullable(input.getUsername()).ifPresent(user::setUsername);
            Optional.ofNullable(input.getEmail()).ifPresent(user::setEmail);
            Optional.ofNullable(input.getPassword()).ifPresent(user::setPassword);
            Optional.ofNullable(input.getRoleName()).ifPresent(user::setUserRole);
            userService.save(user);

            return ResponseFactory.createResponse(
                    "Updated. %s".formatted(userDtoConverter.convertToDto(user)),
                    HttpStatus.OK);
        }
        throw new UserAlreadyCreatedException(username, email);
    }

    @Operation(summary = "Delete the certain user record")
    @Transactional
    @DeleteMapping(
            consumes = ApiConstants.APPLICATION_CONTENT_TYPE,
            produces = ApiConstants.APPLICATION_CONTENT_TYPE,
            value = "/{userId}")
    public ResponseEntity<BaseResponse> deleteUserById(@PathVariable int userId) {
        User user = userService.findByIdOrThrowError(userId);
        userService.delete(user);
        return ResponseFactory.createResponse(
                "User with id %s was deleted.%nAdditional info:%n%s".formatted(
                        userId,
                        userDtoConverter.convertToDto(user)),
                HttpStatus.OK);
    }
}
