package org.jcd2052.restclient.route;

public enum Endpoint {
    GAMES("games");
    private final String route;

    Endpoint(String route) {
        this.route = route;
    }

    public String getRoute() {
        return route;
    }
}
