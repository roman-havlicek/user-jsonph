package cz.havlicek.user.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Properties for JSONPlaceholder connector.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
@Component
@ConfigurationProperties(prefix = "connector.jsonph")
public class JsonPlaceholderProperties {

    // properties with default values
    private String host = "http://jsonplaceholder.typicode.com";
    private String userResource = "/users/{userId}";
    private String postResource = "/posts?userId={userId}";

    public String getHost() {
        return host;
    }

    public void setHost(final String host) {
        this.host = host;
    }

    public String getUserResource() {
        return userResource;
    }

    public void setUserResource(final String userResource) {
        this.userResource = userResource;
    }

    public String getPostResource() {
        return postResource;
    }

    public void setPostResource(final String postResource) {
        this.postResource = postResource;
    }

    /**
     * Returns URI for fetching user info.
     *
     * @return URI for fetching user info
     */
    public String getUserURI() {
        return host + userResource;
    }

    /**
     * Returns URI for fetching user's posts.
     *
     * @return URI for fetching user's posts
     */
    public String getPostURI() {
        return host + postResource;
    }
}
