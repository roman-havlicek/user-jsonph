package cz.havlicek.user.connector.impl;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import cz.havlicek.user.configuration.JsonPlaceholderProperties;
import cz.havlicek.user.connector.Connector;
import cz.havlicek.user.domainobject.Post;
import cz.havlicek.user.domainobject.User;
import cz.havlicek.user.exception.UserNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Implementation of {@link Connector} fetching data from {@code JSONPlaceholder} website.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 * @see <a href="https://jsonplaceholder.typicode.com/">JSONPlaceholder</a>
 */
@Component
public class JsonPlaceholderConnectorImpl implements Connector {

    @Autowired
    private JsonPlaceholderProperties properties;
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public CompletableFuture<User> getUser(Long id) {
        Assert.notNull(id, "id must not be null.");

        try {
            return CompletableFuture.completedFuture(
                    restTemplate.getForObject(properties.getUserURI(), User.class, id));
        } catch (HttpClientErrorException.NotFound e) {
            throw new UserNotFoundException(id);
        }
    }

    @Override
    public CompletableFuture<List<Post>> getPostsForUser(Long userId) {
        Assert.notNull(userId, "userId must not be null.");

        ResponseEntity<List<Post>> response = restTemplate.exchange(
                properties.getPostURI(),
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Post>>() {},
                userId);
        List<Post> posts = response.getBody();
        return CompletableFuture.completedFuture(posts == null ? Collections.emptyList() : posts);
    }
}
