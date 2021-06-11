package app;

import app.api.Menu;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class Main {
    public static void main(String[] args) {
        log.info("started");
        start();
    }

    private static void start() {
        log.info("started");
        var mainMenu = new Menu();
        mainMenu.show();
    }
}
