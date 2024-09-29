package com.huntercodexs.demo.config;

public class ConstantsConfig {
    //GENERIC
    public static final String TARGET_PROTOCOL = "http";
    public static final String TARGET_HOST = "localhost";
    public static final int DEF_PORT = 8080;
    public static final String PROFILE = "dev";
    public static final String URI_USERS = "/api/v1/demo/users";
    public static final String IGNORE_FIELDS = "";

    //USERS INITIAL
    public final static String[][] USERS = new String[][]{
            new String[]{"e1ba320c-182b-4a98-b7ba-0ddb9c478374","John Smith", "john", "1234", "john@email.com"},
            new String[]{"0c2548b1-ad45-4fb6-8edb-d1c2885df455","Mary Smith", "mary", "1234", "mary@email.com"}
    };

    //USERS UPDATE
    public final static String[][] USERS_UP = new String[][]{
            new String[]{"e1ba320c-182b-4a98-b7ba-0ddb9c478374","John Smith UP", "john", "1234", "john.up@email.com"},
            new String[]{"0c2548b1-ad45-4fb6-8edb-d1c2885df455","Mary Smith UP", "mary", "1234", "mary.up@email.com"}
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
