package org.jcd2052.api.controllers;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.jcd2052.api.entities.User;
import org.jcd2052.api.dto.UserDtoInput;
import org.jcd2052.api.factories.UserDtoConverter;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.exceptions.UserAlreadyCreatedException;
import org.jcd2052.api.exceptions.UserNotFoundException;
import org.jcd2052.api.services.UserService;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
@Transactional
public class UsersController {
    private static final String APPLICATION_JSON = "application/json";
    private final UserService userService;
    private final UserDtoConverter userDtoConverter;

    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> fetchUsers(
            @RequestParam Integer userId,
            @RequestParam String userName,
            @RequestParam String email,
            @RequestParam String userRole) {
        User userProbe = User.createUser(userId, userName, email, userRole);
        return ResponseFactory.createResponse(
                userDtoConverter.createDtoListFromEntities(userService.fetchEntities(userProbe)),
                HttpStatus.OK);
    }

    @PostMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> createUser(@RequestBody UserDtoInput input) {
        String username = input.getUsername();
        String email = input.getEmail();

        if (!userService.isUserExistedByNameOrEmail(username, email)) {
            User user = User.builder()
                    .username(username)
                    .password(input.getPassword())
                    .email(email)
                    .userRole(input.getRoleName())
                    .build();
            userService.save(user);
            return ResponseFactory.createResponse(userDtoConverter.convertToDto(user), HttpStatus.CREATED);
        }
        throw new UserAlreadyCreatedException(username, email);
    }

    @PutMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON, value = "/{userId}")
    public ResponseEntity<BaseResponse> updateUser(@PathVariable int userId, @RequestBody UserDtoInput input) {
        String username = Optional.ofNullable(input.getUsername()).orElse(StringUtils.EMPTY);
        String email = Optional.ofNullable(input.getEmail()).orElse(StringUtils.EMPTY);
        Optional<User> userByNameOrEmail = userService.findUserByNameOrEmail(username, email);

        if (userByNameOrEmail.isEmpty()) {
            User user = userService.findByIdOrThrowError(userId);
            Optional.ofNullable(input.getUsername()).ifPresent(user::setUsername);
            Optional.ofNullable(input.getEmail()).ifPresent(user::setEmail);
            Optional.ofNullable(input.getPassword()).ifPresent(user::setPassword);
            Optional.ofNullable(input.getRoleName()).ifPresent(user::setUserRole);
            userService.save(user);

            return ResponseFactory.createResponse(
                    String.format("Updated. %s", userDtoConverter.convertToDto(user)),
                    HttpStatus.OK);
        }
        throw new UserAlreadyCreatedException(userDtoConverter.convertToDto(userByNameOrEmail.get()));
    }

    @DeleteMapping(consumes = APPLICATION_JSON, produces = APPLICATION_JSON, value = "/{userId}")
    public ResponseEntity<BaseResponse> deleteUserById(@PathVariable int userId) {
        User user = userService.findByIdOrThrowError(userId);
        userService.delete(user);
        return ResponseFactory.createResponse(
                String.format(
                        "User with id %s was deleted.%nAdditional info:%n%s",
                        userId,
                        userDtoConverter.convertToDto(user)),
                HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<BaseResponse> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<BaseResponse> handleUserAlreadyCreatedException(UserAlreadyCreatedException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
