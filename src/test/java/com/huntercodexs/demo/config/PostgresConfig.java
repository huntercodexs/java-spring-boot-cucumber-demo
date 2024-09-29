//package com.huntercodexs.demo.config;
//
//import org.springframework.boot.test.util.TestPropertyValues;
//import org.springframework.context.ApplicationContextInitializer;
//import org.springframework.context.ConfigurableApplicationContext;
//import org.springframework.test.context.ContextConfiguration;
//import org.testcontainers.containers.PostgreSQLContainer;
//
//import static com.huntercodexs.demo.config.ConstantsConfig.*;
//
//@ContextConfiguration(initializers = PostgresConfig.EnvInitializer.class)
//public class PostgresConfig {
//
//    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>(DB_CONTAINER_NAME)
//            .withDatabaseName(DB_NAME)
//            .withUsername(DB_USERNAME)
//            .withPassword(DB_PASSWORD)
//            .withExposedPorts(DB_PORT);
//
//    static {
//        postgres.start();
//    }
//
//    static class EnvInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
//        @Override
//        public void initialize(ConfigurableApplicationContext applicationContext) {
//
//            TestPropertyValues.of(
//                    String.format("spring.datasource.url=%s", postgres.getJdbcUrl()),
//                    String.format("spring.datasource.hikari.jdbc-url=%s", postgres.getJdbcUrl()),
//                    String.format("spring.datasource.username=%s", postgres.getUsername()),
//                    String.format("spring.datasource.password=%s", postgres.getPassword())
//            ).applyTo(applicationContext);
//
//        }
//    }
//}
