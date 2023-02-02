package org.jcd2052;

import org.jcd2052.api.models.Game;
import org.jcd2052.dto.DeveloperStudioDto;
import org.jcd2052.dto.GameGenreDto;
import org.jcd2052.dto.GameInfoDto;
import org.jcd2052.dto.PlatformDto;
import org.jcd2052.restclient.service.GameRestService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        GameRestService gameRestService = new GameRestService();
        List<Game> games = gameRestService.getAllGames();
        games.forEach(game -> {
            System.out.println(game.getGameInfo().getAllPlatforms());
            System.out.println(game.getOtherPlatforms());
            System.out.println(game.getGameInfo().getGames());
        });

//        Game game = gameRestService.getGame("my new game test",
//                "Playstation 3");
//        System.out.println(game.getOtherPlatforms());
//        System.out.println(game.getGameInfo().getGames());
//        System.out.println(game.getGameInfo().getAllPlatforms());
//        GameInfoDto body = new GameInfoDto();
//        body.setName("new Game through REST client");
//        body.setGameDescription("some description");
//        body.setGameReleaseDate(2003);
//        body.setGameDeveloperStudio(new DeveloperStudioDto("Studio 1"));
//        body.setGameGenre(new GameGenreDto("Racing"));
//        Set<PlatformDto> platformDtoSet = new HashSet<>();
//        platformDtoSet.add(new PlatformDto(""));
//        platformDtoSet.add(new PlatformDto("Playstation"));
//        body.setPlatforms(platformDtoSet);
//        System.out.println(gameRestService.addGames(body));
//        System.out.println(gameRestService.deleteGame("new Game through REST client",
//                "Playstation"));
//        GameInfoDto body = new GameInfoDto();
//        body.setName("Xbox Only game");
//        body.setGameDescription("some game 7 description");
//        body.setGameReleaseDate(2000);
//        body.setGameDeveloperStudio(new DeveloperStudioDto("Studio 1"));
//        body.setGameGenre(new GameGenreDto("Studio 2"));
//        Set<PlatformDto> platformDtoSet = new HashSet<>();
////        platformDtoSet.add(new PlatformDto("Xbox"));
//        platformDtoSet.add(new PlatformDto("Playstation"));
//        body.setPlatforms(platformDtoSet);
////        System.out.println(gameRestService.addGames(body));
//
//
//        System.out.println(gameRestService.updateGame("Xbox Only game", body));
    }
}
