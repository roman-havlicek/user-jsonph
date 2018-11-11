package cz.havlicek.user.controller;

import java.time.Instant;
import java.util.UUID;

/**
 * Simple holder of the HTTP error response.
 *
 * @author <a href="mailto:roman.havlicek@openwise.cz">Roman Havlicek</a>
 */
public class ErrorResponse {

    private UUID uuid;
    private Instant timestamp;
    private String message;

    public ErrorResponse(String message) {
        this.uuid = UUID.randomUUID();
        this.timestamp = Instant.now();
        this.message = message;
    }

    public UUID getUuid() {
        return uuid;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public String getMessage() {
        return message;
    }
}
