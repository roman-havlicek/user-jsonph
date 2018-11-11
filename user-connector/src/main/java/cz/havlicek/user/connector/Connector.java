package cz.havlicek.user.connector;

import java.util.List;
import java.util.concurrent.CompletableFuture;

import cz.havlicek.user.domainobject.Post;
import cz.havlicek.user.domainobject.User;
import org.springframework.scheduling.annotation.Async;

/**
 * Connector for fetching data from a resource.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
public interface Connector {

    /**
     * Returns information about user with ID {@code id} wrapped in {@link CompletableFuture}.
     *
     * @param id identification of the user
     * @return found {@link User}
     */
    @Async
    CompletableFuture<User> getUser(Long id);

    /**
     * Returns list of all existing posts for a given user {@code userId} wrapped in {@link CompletableFuture}.
     *
     * @param userId identification of the user
     * @return list of {@link Post}s for a given user
     */
    @Async
    CompletableFuture<List<Post>> getPostsForUser(Long userId);


}
