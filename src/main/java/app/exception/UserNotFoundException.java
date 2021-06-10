package app.exception;

import static java.lang.String.format;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(Long id) {
        super(format("User with id %d not found", id));
    }
}
