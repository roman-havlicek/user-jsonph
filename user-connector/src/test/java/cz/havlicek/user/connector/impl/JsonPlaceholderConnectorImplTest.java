package cz.havlicek.user.connector.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;

import java.util.Collections;
import java.util.List;

import cz.havlicek.user.AbstractTest;
import cz.havlicek.user.configuration.JsonPlaceholderProperties;
import cz.havlicek.user.domainobject.Post;
import cz.havlicek.user.domainobject.User;
import cz.havlicek.user.exception.UserNotFoundException;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Test suite for {@link JsonPlaceholderConnectorImpl}.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
class JsonPlaceholderConnectorImplTest extends AbstractTest {

    private static final Long USER_ID = 1L;

    @Mock
    private RestTemplate restTemplateMock;
    @Spy
    private JsonPlaceholderProperties propertiesMock = getProperties();

    @InjectMocks
    private JsonPlaceholderConnectorImpl connector;

    /**
     * Test case for {@link JsonPlaceholderConnectorImpl#getUser(Long)}.
     * Happy scenario.
     */
    @Test
    void testGetUser() throws Exception{
        User fetchedUser = new User(USER_ID, "John Doe", "JD", "jd@mail.org");
        Mockito.when(restTemplateMock.getForObject(anyString(), eq(User.class), eq(USER_ID)))
               .thenReturn(fetchedUser);

        User user = connector.getUser(USER_ID).get();

        assertThat(user).isEqualTo(fetchedUser);

        Mockito.verify(restTemplateMock).getForObject(
                eq(propertiesMock.getUserURI()), eq(User.class), eq(USER_ID));
        Mockito.verifyNoMoreInteractions(restTemplateMock);
    }

    /**
     * Test case for {@link JsonPlaceholderConnectorImpl#getUser(Long)}.
     * Scenario where user is not found.
     */
    @Test
    void testGetUser_userNotFound() {
        Mockito.when(restTemplateMock.getForObject(anyString(), eq(User.class), eq(USER_ID)))
               .thenThrow(HttpClientErrorException.create(HttpStatus.NOT_FOUND, "", new HttpHeaders(), null, null));

        assertThrows(UserNotFoundException.class, () -> connector.getUser(USER_ID), "Expected UserNotFoundException");

        Mockito.verify(restTemplateMock).getForObject(
                eq(propertiesMock.getUserURI()), eq(User.class), eq(USER_ID));
        Mockito.verifyNoMoreInteractions(restTemplateMock);
    }

    /**
     * Test case for {@link JsonPlaceholderConnectorImpl#getPostsForUser(Long)}.
     * Happy scenario.
     */
    @Test
    void testGetPostsForUser() throws Exception {
        Post fetchedPost = new Post(10L, "post title");
        Mockito.when(restTemplateMock.exchange(anyString(), eq(HttpMethod.GET), eq(null), any(ParameterizedTypeReference.class), eq(USER_ID)))
               .thenReturn(ResponseEntity.ok(Collections.singletonList(fetchedPost)));

        List<Post> postsForUser = connector.getPostsForUser(USER_ID).get();

        assertThat(postsForUser).hasSize(1);
        assertThat(postsForUser.get(0)).isEqualTo(fetchedPost);

        Mockito.verify(restTemplateMock).exchange(
                eq(propertiesMock.getPostURI()), eq(HttpMethod.GET), eq(null), any(ParameterizedTypeReference.class), eq(USER_ID));
        Mockito.verifyNoMoreInteractions(restTemplateMock);
    }

    private static JsonPlaceholderProperties getProperties() {
        JsonPlaceholderProperties result = new JsonPlaceholderProperties();
        result.setHost("host");
        result.setUserResource("/users");
        result.setPostResource("/posts");

        return result;
    }

}