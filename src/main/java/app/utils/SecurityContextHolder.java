package app.utils;

import app.entity.user.User;

public class SecurityContextHolder {

    private static User currentUser;
    private static boolean currentUserSignedIn;

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SecurityContextHolder.currentUser = currentUser;
    }

    public static boolean isCurrentUserSignedIn() {
        return currentUserSignedIn;
    }

    public static void setCurrentUserSignedIn(boolean currentUserSignedIn) {
        SecurityContextHolder.currentUserSignedIn = currentUserSignedIn;
    }
}
