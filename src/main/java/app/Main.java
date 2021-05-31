package app;

import app.api.MainMenu;
import app.entity.User;

public class Main {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        var user = new User();
        var mainMenu = new MainMenu();
        mainMenu.showMain(user);
    }
}
