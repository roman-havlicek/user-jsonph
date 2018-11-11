package cz.havlicek.user.service;

import cz.havlicek.user.domainobject.User;

/**
 * Service for retrieving information about user.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
public interface UserService {

    /**
     * Retrieves information about user based on his {@code userId}.
     *
     * @param userId identification of the user
     * @return fetched user
     */
    User getUserInfo(Long userId);
}
