package app.utils;

import static app.entity.user.enums.Role.ADMIN;
import static app.utils.SecurityContextHolder.getCurrentUser;
import static app.utils.SecurityContextHolder.isCurrentUserSignedIn;

public class UserStateValidator {

    public static boolean isSignedIn() {
        return isCurrentUserSignedIn();
    }

    public static boolean isAdmin() {
        return ADMIN.equals(getCurrentUser().getRole());
    }

}
