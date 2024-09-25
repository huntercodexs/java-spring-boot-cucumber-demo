package com.huntercodexs.cucumberdemo.employee.bdd.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public abstract class DeleteAbstractClient extends PutAbstractClient {

    protected void executeDelete(String apiPath) {
        executeDelete(apiPath, null, null);
    }

    protected void executeDelete(String apiPath, Map<String, String> pathParams) {
        executeDelete(apiPath, pathParams, null);
    }

    protected void executeDelete(String apiPath, Map<String, String> pathParams, Map<String, String> queryParams) {
        final RequestSpecification request = CONTEXT.getRequest();
        final Object payload = CONTEXT.getPayload();
        final String url = baseUrl() + apiPath;

        setPayload(request, payload);
        setQueryParams(pathParams, request);
        setPathParams(queryParams, request);

        Response response = request.accept(ContentType.JSON)
                .log()
                .all()
                .delete(url);

        logResponse(response);
        CONTEXT.setResponse(response);
    }
}
