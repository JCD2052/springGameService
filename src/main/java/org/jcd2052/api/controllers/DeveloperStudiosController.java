package org.jcd2052.api.controllers;

import lombok.RequiredArgsConstructor;
import org.jcd2052.api.entities.DeveloperStudio;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.services.DeveloperStudioService;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/studios")
@Transactional(readOnly = true)
public class DeveloperStudiosController {
    private static final String APPLICATION_JSON = "application/json";
    private final DeveloperStudioService developerStudioService;

    @GetMapping(produces = APPLICATION_JSON)
    public ResponseEntity<BaseResponse> getAllStudios() {
        return ResponseFactory.createResponse(
                developerStudioService.findAll()
                        .stream()
                        .map(DeveloperStudio::toDeveloperStudioDto)
                        .toList(),
                HttpStatus.OK);
    }
}