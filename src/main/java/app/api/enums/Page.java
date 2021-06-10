package app.api.enums;

public enum Page {

    PANEL("store | basket | profile | login"),
    PANEL_SIGNED_IN("store | basket | profile | logout"),
    PANEL_ADMIN("store | basket | profile | logout | users"),
    LOGIN("sign in | sign up");

    String panel;

    Page(String panel) {
        this.panel = panel;
    }

    @Override
    public String toString() {
        return panel;
    }
}
