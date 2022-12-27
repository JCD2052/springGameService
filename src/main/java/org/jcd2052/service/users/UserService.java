package org.jcd2052.service.users;

import org.jcd2052.models.UserInfo;
import org.jcd2052.repositories.users.UserRepository;
import org.jcd2052.service.games.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService extends BaseService<UserInfo> {
    @Autowired
    protected UserService(UserRepository repository) {
        super(repository);
    }

    @Override
    protected void updateById(UserInfo entityToBeUpdated, UserInfo entityWithUpdates) {
        entityToBeUpdated.setUsername(entityWithUpdates.getUsername());
        entityToBeUpdated.setPassword(entityWithUpdates.getPassword());
    }
}
