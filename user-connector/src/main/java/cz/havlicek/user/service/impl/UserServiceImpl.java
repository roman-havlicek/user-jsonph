package cz.havlicek.user.service.impl;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import cz.havlicek.user.connector.Connector;
import cz.havlicek.user.domainobject.Post;
import cz.havlicek.user.domainobject.User;
import cz.havlicek.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * Implementation of {@link UserService}.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private Connector connector;

    @Override
    @Cacheable("userInfos")
    public User getUserInfo(final Long userId) {
        CompletableFuture<User> userCf = connector.getUser(userId);

        List<Post> posts = connector.getPostsForUser(userId).join();

        User user = userCf.join();
        user.setPosts(posts);

        return user;
    }
}
