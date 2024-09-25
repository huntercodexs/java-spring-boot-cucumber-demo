package com.huntercodexs.cucumberdemo.employee.bdd.client;

import com.huntercodexs.cucumberdemo.employee.bdd.CucumberTestContext;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.Map;

public abstract class BaseAbstractClient {

    @LocalServerPort
    protected int port;

    protected static final Logger LOG = LoggerFactory.getLogger(BaseAbstractClient.class);

    protected CucumberTestContext CONTEXT = CucumberTestContext.CONTEXT;

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
        if (null != queryParams) {
            request.queryParams(queryParams);
        }
    }

    protected void setQueryParams(Map<String, String> pathParams, RequestSpecification request) {
        if (null != pathParams) {
            request.pathParams(pathParams);
        }
    }

    protected void setPayload(RequestSpecification request, Object payload) {
        if (null != payload) {
            request.contentType(ContentType.JSON)
                    .body(payload);
        }
    }

}
