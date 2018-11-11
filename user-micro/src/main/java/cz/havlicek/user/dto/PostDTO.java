package cz.havlicek.user.dto;

import org.springframework.util.Assert;

/**
 * DTO containing basic information about post.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
public class PostDTO {

    private Long id;
    private String title;

    public PostDTO(Long id, String title) {
        Assert.notNull(id, "Post id must not be null.");
        Assert.hasText(title, "Post title must be present.");

        this.id = id;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }
}
