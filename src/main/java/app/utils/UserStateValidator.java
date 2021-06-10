package app.utils;

import static app.entity.user.enums.Role.ADMIN;
import static app.utils.SecurityContextHolder.getCurrentUser;

public class UserStateValidator {

    private UserStateValidator() {
        throw new IllegalStateException("Utility class");
    }

    public static boolean isAdmin() {
        return ADMIN.equals(getCurrentUser().getRole());
    }

}
