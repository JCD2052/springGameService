package org.jcd2052.api.service.users;

import org.jcd2052.api.service.games.BaseService;
import org.jcd2052.api.entities.UserInfo;
import org.jcd2052.api.repositories.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<UserInfo> {
    @Autowired
    protected UserService(UserRepository repository) {
        super(repository);
    }
}
