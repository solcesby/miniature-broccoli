package app.utils;

import app.entity.user.User;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class SecurityContextHolder {

    private static User currentUser;

    private SecurityContextHolder() {
        log.warn("utility class constructor called");
        throw new IllegalStateException("Utility class");
    }

    public static User getCurrentUser() {
        log.info("getting current user {}", currentUser);
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        SecurityContextHolder.currentUser = currentUser;
        log.info("setting current user to {}", currentUser);
    }

    public static boolean isCurrentUserSignedIn() {
        log.info("checking if current user {} is signed in", currentUser);
        return currentUser != null;
    }
}
