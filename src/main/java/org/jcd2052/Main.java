//package org.jcd2052;
//
//import org.jcd2052.api.entities.Game;
//import org.jcd2052.dto.GameDto;
//import org.jcd2052.restclient.service.GameRestService;
//
//import java.util.List;
//
//public class Main {
//    public static void main(String[] args) {
//        GameRestService gameRestService = new GameRestService();
//        List<GameDto> games = gameRestService.getAllGames();
//        System.out.println(games);
//
//
////        Game game = gameRestService.getGame("my new game test",
////                "Playstation 3");
////        System.out.println(game.getOtherPlatforms());
////        System.out.println(game.getGameInfo().getGames());
////        System.out.println(game.getGameInfo().getAllPlatforms());
////        GameInfoDto body = new GameInfoDto();
////        body.setName("new Game through REST client");
////        body.setGameDescription("some description");
////        body.setGameReleaseDate(2003);
////        body.setGameDeveloperStudio(new DeveloperStudioDto("Studio 1"));
////        body.setGameGenre(new GameGenreDto("Racing"));
////        Set<PlatformDto> platformDtoSet = new HashSet<>();
////        platformDtoSet.add(new PlatformDto(""));
////        platformDtoSet.add(new PlatformDto("Playstation"));
////        body.setPlatforms(platformDtoSet);
////        System.out.println(gameRestService.addGames(body));
////        System.out.println(gameRestService.deleteGame("new Game through REST client",
////                "Playstation"));
////        GameInfoDto body = new GameInfoDto();
////        body.setName("Xbox Only game");
////        body.setGameDescription("some game 7 description");
////        body.setGameReleaseDate(2000);
////        body.setGameDeveloperStudio(new DeveloperStudioDto("Studio 1"));
////        body.setGameGenre(new GameGenreDto("Studio 2"));
////        Set<PlatformDto> platformDtoSet = new HashSet<>();
//////        platformDtoSet.add(new PlatformDto("Xbox"));
////        platformDtoSet.add(new PlatformDto("Playstation"));
////        body.setPlatforms(platformDtoSet);
//////        System.out.println(gameRestService.addGames(body));
////
////
////        System.out.println(gameRestService.updateGame("Xbox Only game", body));
//    }
//}
