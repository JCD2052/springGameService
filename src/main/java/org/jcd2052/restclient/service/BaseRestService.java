package org.jcd2052.restclient.service;

import static io.restassured.RestAssured.given;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.specification.RequestSpecification;
import org.jcd2052.restclient.route.Endpoint;
import org.jcd2052.restclient.utils.ConfigReader;

public abstract class BaseRestService {
    private static final String BASE_URL = ConfigReader.readPropertyFromConfigFile(
            "base_url_api");
    private static final String RESPONSE_TAG = "response";

    private RequestSpecification createRequest() {
        return given()
                .spec(new RequestSpecBuilder()
                        .setBaseUri(BASE_URL)
                        .setBasePath(getBasePath().getRoute())
                        .setContentType(ContentType.JSON)
                        .build());
    }

    protected <T> T getResponse(Method method, String uri, Class<T> responseClass) {
        return createRequest()
                .request(method, uri)
                .jsonPath()
                .getObject(RESPONSE_TAG, responseClass);
    }

    protected <T> T getResponse(Method method, Class<T> responseClass) {
        return getResponse(method, "", responseClass);
    }

    protected abstract Endpoint getBasePath();
}