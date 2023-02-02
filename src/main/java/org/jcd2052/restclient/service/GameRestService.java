package org.jcd2052.restclient.service;

import io.restassured.http.Method;
import org.jcd2052.dto.GameDto;
import org.jcd2052.restclient.route.Endpoint;

import java.util.Arrays;
import java.util.List;

public class GameRestService extends BaseRestService {

    public List<GameDto> getAllGames() {
        return Arrays.asList(getResponse(Method.GET, GameDto[].class));
    }

    @Override
    protected Endpoint getBasePath() {
        return Endpoint.GAMES;
    }
}
