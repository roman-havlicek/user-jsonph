package cz.havlicek.user.domainobject;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Domain object holding information about user.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {

    private Long id;
    private String name;
    private String username;
    private String email;
    private List<Post> posts;

    /**
     * Empty protected constructor for deserialization purposes.
     */
    protected User() {}

    /**
     * Constructor for setting all fields except {@code posts}, which can be set through setter.
     *
     * @param id       user's identifier
     * @param name     user's name
     * @param username username
     * @param email    user's e-mail address
     */
    public User(Long id, String name, String username, String email) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
    }

    /**
     * Gets user's identifier.
     *
     * @return user's identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets user's name.
     *
     * @return user's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets username of this user.
     *
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets user's e-mail address.
     *
     * @return user's e-mail
     */
    public String getEmail() {
        return email;
    }

    /**
     * Returns all user's posts.
     *
     * @return list of all user's posts
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Sets list of posts for this user.
     *
     * @param posts list of posts to be set
     */
    public void setPosts(final List<Post> posts) {
        this.posts = posts;
    }
}
