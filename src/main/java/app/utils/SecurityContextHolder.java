package app.utils;

import app.entity.user.User;

public class SecurityContextHolder {

    private static User currentUser;

    private SecurityContextHolder() {
        throw new IllegalStateException("Utility class");
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SecurityContextHolder.currentUser = currentUser;
    }

    public static boolean isCurrentUserSignedIn() {
        return currentUser != null;
    }
}
