package cz.havlicek.user.exception;

import java.text.MessageFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.http.HttpStatus;

/**
 * Exception thrown when user for a given ID was not found.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
@JsonIgnoreProperties(value = {"cause", "suppressed", "stackTrace"})
public class UserNotFoundException extends RuntimeException {

    private static final String MESSAGE = "User with ID ''{0}'' not found.";

    public UserNotFoundException(Long userId) {
        super(MessageFormat.format(MESSAGE, userId));
    }

    public HttpStatus getStatus() {
        return HttpStatus.NOT_FOUND;
    }
}
