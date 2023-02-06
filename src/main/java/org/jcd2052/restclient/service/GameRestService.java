//package org.jcd2052.restclient.service;
//
//import org.jcd2052.api.entities.Game;
//import org.jcd2052.dto.GameDto;
//import org.jcd2052.dto.GameInfoDto;
//import org.jcd2052.restclient.route.Endpoint;
//
//import java.util.Arrays;
//import java.util.List;
//
//public class GameRestService extends BaseRestService {
//
//    public List<GameDto> getAllGames() {
//        return Arrays.asList(getResponse(GameDto[].class));
//    }
//
//    public Game getGame(String gameName, String platformName) {
//        return getResponse(String.join("/", platformName, gameName), (Game.class));
//    }
//
//    public List<GameDto> addGames(GameInfoDto gameInfoDto) {
//        return Arrays.asList(postResponse(gameInfoDto, GameDto[].class));
//    }
//
//    public String deleteGame(String gameName, String platformName) {
//        return deleteResponse(String.join("/", platformName, gameName), String.class);
//    }
//
//    public GameInfoDto updateGame(String gameName, GameInfoDto body) {
//        return patchResponse(body, gameName, GameInfoDto.class);
//    }
//
//    @Override
//    protected Endpoint getBasePath() {
//        return Endpoint.GAMES;
//    }
//}
