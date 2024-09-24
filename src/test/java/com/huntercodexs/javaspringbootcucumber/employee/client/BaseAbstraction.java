package com.huntercodexs.javaspringbootcucumber.employee.client;

import com.huntercodexs.javaspringbootcucumber.employee.context.CucumberTestContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Map;

public class BaseAbstraction {

    @LocalServerPort
    protected int port;

    protected final CucumberTestContext CONTEXT = CucumberTestContext.CONTEXT;
    protected static final Logger LOG = LoggerFactory.getLogger(BaseAbstraction.class);

    protected String baseUrl() {
        return "http://localhost:" + port;
    }

    protected CucumberTestContext testContext() {
        return CONTEXT;
    }

    protected void logResponse(Response response) {
        response.then()
                .log()
                .all();
    }

    protected void setPathParams(Map<String, String> queryParams, RequestSpecification request) {
        if (queryParams != null) {
            request.queryParams(queryParams);
        }
    }

    protected void setQueryParams(Map<String, String> pathParams, RequestSpecification request) {
        if (pathParams != null) {
            request.pathParams(pathParams);
        }
    }

    protected void setPayload(RequestSpecification request, Object payload) {
        if (payload != null) {
            request.contentType(ContentType.JSON)
                    .body(payload);
        }
    }
}
