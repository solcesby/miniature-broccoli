package app;

import app.api.MainMenu;

import static app.api.enums.Page.MAIN;

public class Main {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        MainMenu mainMenu = new MainMenu();
        mainMenu.showPage(MAIN);
    }
}
