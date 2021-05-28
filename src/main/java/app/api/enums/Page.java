package app.api.enums;

import java.util.List;

public enum Page {

    MAIN("(1) store | (2) basket | (3) profile | (4) login"),
    STORE("(1) main"),
    BASKET("(1) main"),
    PROFILE("(1) main"),
    LOGIN("(1) sign in | (2) sign up"),
    SIGN_IN("(1) main"),
    SIGN_UP("(1) main");

    private final String panel;
    private final int panelSize;
    private List<Page> pages;

    Page(String panel) {
        this.panel = panel;
        this.panelSize = panel.split("\\|").length;
    }

    static {
        MAIN.pages = List.of(STORE, BASKET, PROFILE, LOGIN);
        STORE.pages = List.of(MAIN);
        BASKET.pages = List.of(MAIN);
        PROFILE.pages = List.of(MAIN);
        LOGIN.pages = List.of(SIGN_IN, SIGN_UP);
        SIGN_IN.pages = List.of(MAIN);
        SIGN_UP.pages = List.of(MAIN);
    }

    public String getPanel() {
        return panel;
    }

    public int getPanelSize() {
        return panelSize;
    }

    public List<Page> getPages() {
        return pages;
    }
}
