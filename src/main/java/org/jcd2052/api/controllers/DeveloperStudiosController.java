package org.jcd2052.api.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.jcd2052.api.constants.ApiConstants;
import org.jcd2052.api.dto.output.DeveloperStudioDto;
import org.jcd2052.api.entities.DeveloperStudio;
import org.jcd2052.api.dtoconverters.DeveloperStudioDtoConverter;
import org.jcd2052.api.repsonses.BaseResponse;
import org.jcd2052.api.repsonses.ResponseFactory;
import org.jcd2052.api.services.DeveloperStudioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/studios")
@Transactional(readOnly = true)
@Tag(name = "Studio")
public class DeveloperStudiosController {
    private final DeveloperStudioService developerStudioService;
    private final DeveloperStudioDtoConverter developerStudioDtoConverter;

    @Operation(summary = "Fetch developer studios")
    @GetMapping(produces = ApiConstants.APPLICATION_CONTENT_TYPE)
    @ApiResponses({
            @ApiResponse(
                    description = "Fetch users by parameters or returns an empty list",
                    responseCode = "200",
                    content = {@Content(schema = @Schema(implementation = DeveloperStudioDto.class),
                            mediaType = ApiConstants.APPLICATION_CONTENT_TYPE)})})
    public ResponseEntity<BaseResponse> fetchStudios(
            @RequestParam(required = false) Long studioId,
            @RequestParam(required = false) String studioName) {
        DeveloperStudio developerStudioProbe = DeveloperStudio.createStudio(studioId, studioName);
        return ResponseFactory.createResponse(
                developerStudioDtoConverter.createDtoListFromEntities(
                        developerStudioService.fetchEntities(developerStudioProbe)),
                HttpStatus.OK);
    }
}