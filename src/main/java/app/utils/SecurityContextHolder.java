package app.utils;

import app.entity.user.User;

public class SecurityContextHolder {

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void setUser(User user) {
        SecurityContextHolder.user = user;
    }
}
