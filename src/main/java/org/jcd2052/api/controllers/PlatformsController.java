package org.jcd2052.api.controllers;

import lombok.RequiredArgsConstructor;
import org.jcd2052.api.entities.Platform;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.services.PlatformService;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/platforms")
@Transactional(readOnly = true)
public class PlatformsController {
    private static final String APPLICATION_JSON = "application/json";
    private final PlatformService platformService;

    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> getAllPlatform() {
        return ResponseFactory.createResponse(
                platformService.fetchAll()
                        .stream()
                        .map(Platform::toPlatformDto)
                        .toList(),
                HttpStatus.OK);
    }
}
