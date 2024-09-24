package com.huntercodexs.javaspringbootcucumber.employee.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class GetAbstraction extends PostAbstraction {

    protected void executeGet(String apiPath) {
        executeGet(apiPath, null, null);
    }

    protected void executeGet(String apiPath, Map<String, String> pathParams) {
        executeGet(apiPath, pathParams, null);
    }

    protected void executeGet(String apiPath, Map<String, String> pathParams, Map<String, String> queryParams) {
        final RequestSpecification request = CONTEXT.getRequest();
        final String url = baseUrl() + apiPath;

        setQueryParams(pathParams, request);
        setPathParams(queryParams, request);

        Response response = request.accept(ContentType.JSON)
                .log()
                .all()
                .get(url);

        logResponse(response);
        CONTEXT.setResponse(response);
    }
}
