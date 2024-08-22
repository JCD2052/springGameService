package org.jcd2052.api.controllers;

import org.jcd2052.api.entities.DeveloperStudio;
import org.jcd2052.api.factories.GameDtoFactory;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.service.games.DeveloperStudioService;
import org.jcd2052.api.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/studio")
@Transactional(readOnly = true)
public class DeveloperStudioController {
    private static final String APPLICATION_JSON = "application/json";
    private final DeveloperStudioService developerStudioService;

    @Autowired
    public DeveloperStudioController(DeveloperStudioService developerStudioService) {
        this.developerStudioService = developerStudioService;
    }

    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> getAllStudios() {
        return Utils.createResponse(
                developerStudioService.getAll()
                        .stream()
                        .map(DeveloperStudio::toDeveloperStudioDto)
                        .collect(Collectors.toList()),
                HttpStatus.OK);
    }

    @GetMapping(value = "/{studioId}/games")
    public ResponseEntity<BaseResponse> getGamesByStudio(@PathVariable int studioId) {
        return Utils.createResponse(
                GameDtoFactory.createGameDtoList(developerStudioService.findDeveloperStudioById(studioId).getGames()),
                HttpStatus.OK);
    }
}