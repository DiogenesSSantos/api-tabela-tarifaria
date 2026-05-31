package com.gitgub.diogenesssantos.api.config;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.MapPropertySource;
import org.testcontainers.postgresql.PostgreSQLContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.Map;

public class TestcontainersConfiguration {

    public static final PostgreSQLContainer POSTGRES;

    static {
        POSTGRES = new PostgreSQLContainer(DockerImageName.parse("postgres:latest"))
                .withDatabaseName("tarifadb")
                .withUsername("postgres")
                .withPassword("123");
        POSTGRES.start();
    }

    public static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurationApplication) {
            Map<String, Object> props = new HashMap<>();
            props.put("spring.datasource.url", POSTGRES.getJdbcUrl());
            props.put("spring.datasource.username", POSTGRES.getUsername());
            props.put("spring.datasource.password", POSTGRES.getPassword());
            props.put("spring.flyway.locations", "classpath:db/migration/test");

            configurationApplication.getEnvironment().getPropertySources()
                    .addFirst(new MapPropertySource("testcontainers", props));
        }

    }
}

