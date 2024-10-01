package org.jcd2052.api.auth;

import org.jcd2052.api.entities.User;
import org.jcd2052.api.entities.UserDetails;
import org.springframework.security.core.Authentication;

public interface IAuthenticationFacade {
    Authentication getAuthentication();

    default User getAuthenticatedUser() {
        return ((UserDetails) getAuthentication().getPrincipal()).getUser();
    }
}

