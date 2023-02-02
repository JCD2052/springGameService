package org.jcd2052;

import org.jcd2052.restclient.service.GameRestService;

public class Main {
    public static void main(String[] args) {
        GameRestService gameRestService = new GameRestService();
        System.out.println(gameRestService.getAllGames());
    }
}
