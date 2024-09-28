package com.huntercodexs.demo.container;

import org.testcontainers.containers.PostgreSQLContainer;

import static com.huntercodexs.demo.config.ConstantsConfig.DB_CONTAINER_VERSION;

public class PostgresSqlContainerSettings extends PostgreSQLContainer<PostgresSqlContainerSettings> {

    private static PostgresSqlContainerSettings container;

    private PostgresSqlContainerSettings() {
        super(DB_CONTAINER_VERSION);
    }

    public static PostgresSqlContainerSettings getInstance() {
        if (container == null) {
            container = new PostgresSqlContainerSettings().withReuse(true);
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
    }

    @Override
    public void stop() {
        // do nothing, JVM handles shut down
    }
}
