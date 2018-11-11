/*
 * The code is property of OpenWise Solutions, s.r.o.
 * http://www.openwise.cz, Copyright 2018
 */

package cz.havlicek.user.dto;

import java.util.List;

import org.springframework.util.Assert;

/**
 * DTO containing basic information about user.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
public class UserDTO {

    private Long id;
    private String name;
    private String username;
    private String email;
    private List<PostDTO> posts;

    public UserDTO(Long id, String name, String username, String email, List<PostDTO> posts) {
        Assert.notNull(id, "User id must not be null.");
        Assert.notNull(posts, "User posts must not be null.");

        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.posts = posts;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public List<PostDTO> getPosts() {
        return posts;
    }
}
