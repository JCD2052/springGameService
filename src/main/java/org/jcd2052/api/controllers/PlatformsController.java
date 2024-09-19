package org.jcd2052.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.dto.output.PlatformDto;
import org.jcd2052.api.entities.Platform;
import org.jcd2052.api.dtoconverters.PlatformDtoConverter;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.services.PlatformService;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/platforms")
@Transactional(readOnly = true)
@Tag(name = "Platforms")
public class PlatformsController {
    private final PlatformService platformService;
    private final PlatformDtoConverter platformDtoConverter;

    @Operation(summary = "Fetch platform records")
    @GetMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    @ApiResponses({
            @ApiResponse(
                    description = "Fetch platforms by parameters or returns an empty list",
                    responseCode = "200",
                    content = {@Content(schema = @Schema(name = "Platform records response",
                            implementation = PlatformDto.class),
                            mediaType = ApiConstants.APPLICATION_CONTENT_TYPE)})})
    public ResponseEntity<BaseResponse> fetchPlatforms(
            @RequestParam(required = false) Integer platformId,
            @RequestParam(required = false) String platformName) {
        Platform platformProbe = Platform.createPlatform(platformId, platformName);
        return ResponseFactory.createResponse(
                platformDtoConverter.createDtoListFromEntities(platformService.fetchEntities(platformProbe)),
                HttpStatus.OK);
    }
}
