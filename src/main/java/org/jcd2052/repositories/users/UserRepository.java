package org.jcd2052.repositories.users;

import org.jcd2052.models.UserInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserInfo, Integer> {
}
