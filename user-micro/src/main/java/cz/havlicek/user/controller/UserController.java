package cz.havlicek.user.controller;

import cz.havlicek.user.dto.UserDTO;
import cz.havlicek.user.mapper.UserMapper;
import cz.havlicek.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for user resource.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/{userId}")
    public UserDTO getUserInfo(@PathVariable Long userId) {
        Assert.notNull(userId, "User ID must not be null.");

        return UserMapper.toUserDTO(userService.getUserInfo(userId));
    }
}
