//package com.huntercodexs.demo.container;
//
//import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
//import org.testcontainers.containers.PostgreSQLContainer;
//import org.testcontainers.junit.jupiter.Container;
//import org.testcontainers.junit.jupiter.Testcontainers;
//import org.testcontainers.utility.DockerImageName;
//
//import static com.huntercodexs.demo.config.ConstantsConfig.DB_CONTAINER_NAME;
//import static com.huntercodexs.demo.config.ConstantsConfig.DB_CONTAINER_VERSION;
//
//@Testcontainers
//public class PostgresContainerSettings {
//
//    @Container
//    @ServiceConnection
//    public static PostgreSQLContainer<?> postgres;
//
//    static {
//        DockerImageName image = DockerImageName
//                .parse(DB_CONTAINER_NAME)
//                .withTag(DB_CONTAINER_VERSION);
//        postgres = new PostgreSQLContainer<>(image);
//        postgres.start();
//    }
//
//}
