package com.huntercodexs.javaspringbootcucumber.employee.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public class PutAbstraction extends DeleteAbstraction {

    protected void executePut(String apiPath) {
        executePut(apiPath, null, null);
    }

    protected void executePut(String apiPath, Map<String, String> pathParams) {
        executePut(apiPath, pathParams, null);
    }

    protected void executePut(String apiPath, Map<String, String> pathParams, Map<String, String> queryParams) {
        final RequestSpecification request = CONTEXT.getRequest();
        final Object payload = CONTEXT.getPayload();
        final String url = baseUrl() + apiPath;

        setPayload(request, payload);
        setQueryParams(pathParams, request);
        setPathParams(queryParams, request);

        Response response = request.accept(ContentType.JSON)
                .log()
                .all()
                .put(url);

        logResponse(response);
        CONTEXT.setResponse(response);
    }
}
