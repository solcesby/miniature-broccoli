package app;

import app.api.MainMenu;

public class Main {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.showMainPage();
    }
}
