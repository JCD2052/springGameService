package org.jcd2052.api.controllers;

import org.jcd2052.api.entities.Platform;
import org.jcd2052.api.factories.GameDtoFactory;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.exceptions.PlatformNotFoundException;
import org.jcd2052.api.services.PlatformService;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/api/platforms")
@Transactional(readOnly = true)
public class PlatformsController {
    private static final String APPLICATION_JSON = "application/json";
    private final PlatformService platformService;

    @Autowired
    public PlatformsController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> getAllPlatform() {
        return ResponseFactory.createResponse(
                platformService.findAll()
                        .stream()
                        .map(Platform::toPlatformDto)
                        .toList(),
                HttpStatus.OK);
    }

    @GetMapping(value = "/{platformId}/games")
    public ResponseEntity<BaseResponse> getGamesByPlatformName(@PathVariable int platformId) {
        return ResponseFactory.createResponse(
                GameDtoFactory.createGameDtoList(platformService.findPlatformByIdOrThrowError(platformId).getGames()),
                HttpStatus.OK);
    }

    @ExceptionHandler
    private ResponseEntity<BaseResponse> handlePlatformNotFoundException(
            PlatformNotFoundException exception) {
        return ResponseFactory.createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
    }
}
