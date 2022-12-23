package org.jcd2052.queris;

public class Queries {
    public static final String GET_ALL_GAME_INFO = "select gi from game_info gi " +
            "LEFT JOIN FETCH gi.platforms " +
            "LEFT JOIN FETCH gi.genre " +
            "LEFT JOIN FETCH gi.developerStudio";
    public static final String GET_ALL_PLATFORM = "select p from platform p";
    public static final String GET_ALL_GENRES = "select g from game_genre g";
    public static final String GET_ALL_DEVELOPER_STUDIOS = "select d from developer_studio d";

    private Queries() {
    }
}
