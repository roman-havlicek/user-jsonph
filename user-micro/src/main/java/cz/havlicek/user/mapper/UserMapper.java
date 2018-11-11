/*
 * The code is property of OpenWise Solutions, s.r.o.
 * http://www.openwise.cz, Copyright 2018
 */

package cz.havlicek.user.mapper;

import java.util.stream.Collectors;

import cz.havlicek.user.domainobject.Post;
import cz.havlicek.user.domainobject.User;
import cz.havlicek.user.dto.PostDTO;
import cz.havlicek.user.dto.UserDTO;

/**
 * Class for mapping between domain objects and DTOs.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
public final class UserMapper {

    private UserMapper() {
        // cannot instantiate
    }

    /**
     * Maps domain objects {@link User} and collection of {@link Post}s into {@link UserDTO}.
     *
     * @param user information about selected user with list of {@link Post} of a selected user
     * @return new instance of mapped {@link UserDTO}
     */
    public static UserDTO toUserDTO(User user) {
        return new UserDTO(
                user.getId(), user.getName(), user.getUsername(), user.getEmail(),
                user.getPosts().stream().map(UserMapper::toPostDTO).collect(Collectors.toList()));
    }

    private static PostDTO toPostDTO(Post post) {
        return new PostDTO(post.getId(), post.getTitle());
    }
}
