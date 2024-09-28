package com.huntercodexs.demo.client;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.github.dzieciou.testing.curl.CurlRestAssuredConfigFactory;
import com.github.dzieciou.testing.curl.Options;
import io.restassured.config.ObjectMapperConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import static io.restassured.RestAssured.given;

@Slf4j
@Component
@RequiredArgsConstructor
public class RestClient {

    public RequestSpecification getRequestSpecification() {

        int port = Integer.parseInt(System.getProperty("port"));

        Options options = Options.builder()
                .printMultiliner()
                .updateCurl(curl -> curl
                        .removeHeader("Host")
                        .removeHeader("User-Agent")
                        .removeHeader("Connection")
                        .removeHeader("Keep-Alive"))
                .build();

        RestAssuredConfig config = CurlRestAssuredConfigFactory
                .createConfig(options)
                .objectMapperConfig(new ObjectMapperConfig().jackson2ObjectMapperFactory(
                        (type, charset) -> {
                            ObjectMapper om = new ObjectMapper().findAndRegisterModules();
                            om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
                            om.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
                            return om;
                        }));

        return given()
                .config(config)
                .port(port)
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .urlEncodingEnabled(false)
                .when()
                .log()
                .everything();
    }

}
