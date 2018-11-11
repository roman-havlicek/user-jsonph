package cz.havlicek.user.domainobject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.util.Assert;

/**
 * Domain object holding information about single post.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Post {

    private Long id;
    private String title;

    /**
     * Empty protected constructor for deserialization purposes.
     */
    protected Post() {}

    /**
     * Constructor for all fields.
     *
     * @param id    post identifier
     * @param title post title
     */
    public Post(Long id, String title) {
        Assert.notNull(id, "Post ID must not be null.");
        Assert.hasText(title, "Post title must be present.");

        this.id = id;
        this.title = title;
    }

    /**
     * Gets post's identifier.
     *
     * @return post's identifier
     */
    public Long getId() {
        return id;
    }

    /**
     * Gets post's title.
     *
     * @return post's title
     */
    public String getTitle() {
        return title;
    }
}
