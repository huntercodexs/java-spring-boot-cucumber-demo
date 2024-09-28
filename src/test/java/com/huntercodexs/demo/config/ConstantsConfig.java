package com.huntercodexs.demo.config;

import java.util.UUID;

public class ConstantsConfig {
    //GENERIC
    public static final String PROTOCOL = "http";
    public static final String APP_HOST = "localhost";
    public static final int DEF_PORT = 8080;
    public static final String ENV = "dev";
    public static final String URI_USERS = "/api/v1/demo/users";
    public static final String IGNORE_FIELDS = "";

    //USERS INITIAL
    public final static String[][] USERS = new String[][]{
            new String[]{"27f18116-0ceb-4b34-8a2f-210747d25506", "John Smith", "john", "1234", "john@email.com"},
            new String[]{"8db7eefa-410e-40d6-94f4-f1d01fde1b06", "Mary Smith", "mary", "1234", "mary@email.com"}
    };

    //USERS UPDATE
    public final static String[][] USERS_UP = new String[][]{
            new String[]{"27f18116-0ceb-4b34-8a2f-210747d25506", "John Smith UP", "john", "1234", "john.up@email.com"},
            new String[]{"8db7eefa-410e-40d6-94f4-f1d01fde1b06", "Mary Smith UP", "mary", "1234", "mary.up@email.com"}
    };

    //BROKE
    public static final String PACT_URL_MOCK = "http://localhost:8082";
    public static final String PACT_PORT_MOCK = "8082";
    public static final String PACT_PROVIDER = "PACT_PROVIDER";
    public static final String PACT_CONSUMER = "PACT_CONSUMER";
    public static final String PACT_USERNAME = "pact_username";
    public static final String PACT_PASSWORD = "pact_password";

    //DATABASE
    public static final Integer DB_PORT = 5432;
    public static final String DB_JDBC_URL = "jdbc:postgresql://192.168.0.204:5432/postgres";
    public static final String DB_NAME = "postgres";
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "pgsql1Ipw";

    //CONTAINER
    public static final String DB_CONTAINER_NAME = "postgres";
    //private static final String DB_CONTAINER_VERSION = "postgres:13.5";
    public static final String DB_CONTAINER_VERSION = "latest";
}
