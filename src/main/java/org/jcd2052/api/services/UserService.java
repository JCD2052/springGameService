package org.jcd2052.api.services;

import org.jcd2052.api.entities.User;
import org.jcd2052.api.repositories.UserRepository;
import org.jcd2052.api.exceptionhandler.exceptions.UserNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<User> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    public User findByIdOrThrowError(int userId) {
        return repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public User findUserByNameOrEmailOrThrowError(String username, String email) {
        return ((UserRepository) repository).findUserByUsernameOrEmail(username, email)
                .orElseThrow(() -> new UserNotFoundException(username, email));
    }

    public boolean isUserExistedByNameOrEmail(String username, String email) {
        return ((UserRepository) repository).findUserByUsernameOrEmail(username, email).isPresent();
    }
}
