package app.utils.validators;

import app.entity.user.User;

import java.util.regex.Pattern;

import static app.utils.Constants.*;

public class UserValidator {
    public static void validate(final User user) {
        if (!Pattern.matches(EMAIL_REGEX, user.getEmail())) {
            System.out.println("Incorrect email!");
            throw new IllegalArgumentException("Incorrect email!");
        }
        if (!Pattern.matches(PASSWORD_REGEX, user.getPassword())) {
            System.out.println("Incorrect password!");
            throw new IllegalArgumentException("Incorrect password!");
        }
        if (!Pattern.matches(NAME_REGEX, user.getName())) {
            System.out.println("Incorrect name!");
            throw new IllegalArgumentException("Incorrect name!");
        }
        if (!Pattern.matches(NAME_REGEX, user.getLastName())) {
            System.out.println("Incorrect last name!");
            throw new IllegalArgumentException("Incorrect last name!");
        }
    }
}
