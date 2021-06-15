package app.utils.validators;

import lombok.extern.log4j.Log4j2;

import static app.entity.user.enums.Role.ADMIN;
import static app.utils.SecurityContextHolder.getCurrentUser;

@Log4j2
public class UserStateValidator {

    private UserStateValidator() {
        log.warn("utility class constructor called");
        throw new IllegalStateException("Utility class");
    }

    public static boolean isAdmin() {
        log.info("checking role of user {}", getCurrentUser());
        return ADMIN.equals(getCurrentUser().getRole());
    }

}
