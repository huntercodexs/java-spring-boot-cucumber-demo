package com.huntercodexs.demo.util;

import java.util.UUID;

public class Constants {
    public static final UUID USER_ID = UUID.fromString("d3256c76-62d7-4481-9d1c-a0ccc4da380f");

    public static final String MOCK_PACT_URL = "http://localhost:8082";
    public static final String MOCK_PACT_PORT = "8082";

    public static final String PACT_PROVIDER = "MY_PROVIDER";
    public static final String PACT_CONSUMER = "MY_CONSUMER";
    public static final String PACT_USERNAME = "usernamed";
    public static final String PACT_PASSWORD = "passwordd";

    public static final Integer DB_PORT = 5432;
    public static final String DB_JDBC_URL = "jdbc:postgresql://192.168.0.204:5432/postgres";
    public static final String DB_NAME = "postgres";
    public static final String DB_USERNAME = "postgres";
    public static final String DB_PASSWORD = "pgsql1Ipw";
    public static final String DB_CONTAINER_NAME = "postgres";
}
