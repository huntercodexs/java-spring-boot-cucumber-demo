package com.huntercodexs.cucumberdemo.employee.bdd.client;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;

public abstract class PatchAbstractClient extends BaseAbstractClient {

    protected void executePatch(String apiPath) {
        executePatch(apiPath, null, null);
    }

    protected void executePatch(String apiPath, Map<String, String> pathParams) {
        executePatch(apiPath, pathParams, null);
    }

    protected void executePatch(String apiPath, Map<String, String> pathParams, Map<String, String> queryParams) {
        final RequestSpecification request = CONTEXT.getRequest();
        final Object payload = CONTEXT.getPayload();
        final String url = baseUrl() + apiPath;

        setPayload(request, payload);
        setQueryParams(pathParams, request);
        setPathParams(queryParams, request);

        Response response = request.accept(ContentType.JSON)
                .log()
                .all()
                .patch(url);

        logResponse(response);
        CONTEXT.setResponse(response);
    }
}
