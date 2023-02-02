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

    private <T> T getBaseResponse(Method method, String uri, Class<T> responseClass) {
        return createRequest()
                .request(method, uri)
                .jsonPath()
                .getObject(RESPONSE_TAG, responseClass);
    }

    private <T> T getBaseResponse(Method method, Class<T> responseClass) {
        return getBaseResponse(method, "", responseClass);
    }

    protected <T> T getResponse(String uri, Class<T> responseClass) {
        return getBaseResponse(Method.GET, uri, responseClass);
    }

    protected <T> T getResponse(Class<T> responseClass) {
        return getBaseResponse(Method.GET, responseClass);
    }

    protected <T> T postResponse(Object body, String uri, Class<T> responseClass) {
        return createRequest()
                .body(body)
                .post("")
                .jsonPath()
                .getObject(RESPONSE_TAG, responseClass);
    }

    protected <T> T postResponse(Object body, Class<T> responseClass) {
        return postResponse(body, "", responseClass);
    }

    protected <T> T patchResponse(Object body, String uri, Class<T> responseClass) {
        return createRequest()
                .body(body)
                .patch(uri)
                .jsonPath()
                .getObject(RESPONSE_TAG, responseClass);
    }

    protected <T> T patchResponse(Object body, Class<T> responseClass) {
        return patchResponse(body, "", responseClass);
    }

    protected <T> T deleteResponse(String uri, Class<T> responseClass) {
        return getBaseResponse(Method.DELETE, uri, responseClass);
    }

    protected <T> T deleteResponse(Class<T> responseClass) {
        return getBaseResponse(Method.DELETE, responseClass);
    }

    protected abstract Endpoint getBasePath();
}