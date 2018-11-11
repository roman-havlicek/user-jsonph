package cz.havlicek.user.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;

import cz.havlicek.user.AbstractTest;
import cz.havlicek.user.connector.Connector;
import cz.havlicek.user.domainobject.Post;
import cz.havlicek.user.domainobject.User;
import cz.havlicek.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

/**
 * Test suite for {@link UserServiceImpl}.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
class UserServiceImplTest extends AbstractTest {

    @Mock
    private Connector connectorMock;

    @InjectMocks
    private UserServiceImpl service;

    /**
     * Happy scenario for {@link UserServiceImpl#getUserInfo(Long)}.
     */
    @Test
    void testGetUserInfo() {
        Long userId = 1L;
        User fetchedUser = new User(userId, "John Doe", "JD", "jd@mail.org");
        List<Post> fetchedPosts = Arrays.asList(
                new Post(10L, "First post"),
                new Post(11L, "Second post")
        );

        Mockito.when(connectorMock.getUser(any()))
               .thenReturn(CompletableFuture.completedFuture(fetchedUser));
        Mockito.when(connectorMock.getPostsForUser(any()))
               .thenReturn(CompletableFuture.completedFuture(fetchedPosts));

        User userInfo = service.getUserInfo(userId);

        assertThat(userInfo).isNotNull();
        assertThat(userInfo.getId()).isEqualTo(userId);
        assertThat(userInfo.getName()).isEqualTo(fetchedUser.getName());
        assertThat(userInfo.getUsername()).isEqualTo(fetchedUser.getUsername());
        assertThat(userInfo.getEmail()).isEqualTo(fetchedUser.getEmail());
        assertThat(userInfo.getPosts()).hasSize(2);
        assertThat(userInfo.getPosts()).contains(fetchedPosts.get(0), fetchedPosts.get(1));

        Mockito.verify(connectorMock).getUser(userId);
        Mockito.verify(connectorMock).getPostsForUser(userId);
        Mockito.verifyNoMoreInteractions(connectorMock);
    }

    /**
     * Scenario for {@link UserServiceImpl#getUserInfo(Long)} where user is not found.
     */
    @Test
    void testGetUserInfo_userNotFound() {
        Long userId = 1L;

        Mockito.when(connectorMock.getUser(any()))
               .thenThrow(new UserNotFoundException(userId));

        assertThrows(UserNotFoundException.class, () -> service.getUserInfo(userId), "UserNotFoundException expected");

        Mockito.verify(connectorMock).getUser(userId);
        Mockito.verifyNoMoreInteractions(connectorMock);
    }

}