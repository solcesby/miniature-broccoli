package app;

import app.api.Menu;

public class Main {
    public static void main(String[] args) {
        start();
    }

    private static void start() {
        var mainMenu = new Menu();
        mainMenu.show();
    }
}
