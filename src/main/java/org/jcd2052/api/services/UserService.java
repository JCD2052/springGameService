package org.jcd2052.api.services;

import org.jcd2052.api.entities.User;
import org.jcd2052.api.repositories.UserRepository;
import org.jcd2052.api.repsonses.exceptionhandler.exception.UserNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService extends BaseService<User> {
    public UserService(UserRepository repository) {
        super(repository);
    }

    public User findByIdOrThrowError(int userId) {
        return repository.findById(userId).orElseThrow(() -> new UserNotFoundException(userId));
    }

    public Optional<User> findUserByNameOrEmail(String username, String email) {
        return ((UserRepository) repository).findUserByUsernameOrEmail(username, email);
    }

    public boolean isUserExistedByNameOrEmail(String username, String email) {
        return findUserByNameOrEmail(username, email).isPresent();
    }
}
