package com.algaworks.algashop.ordering.utils;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.testcontainers.containers.PostgreSQLContainer;

@TestConfiguration
public class TestcontainerPostgreSQLConfig {

    public static PostgreSQLContainer postgreSQLContainer =
            new PostgreSQLContainer("postgres:17-alpine");

    static {
        postgreSQLContainer.start(); //Necessário para evitar erro ao tentar buscar propriedades de um container que não iniciou
    }

    @Bean
//    @ServiceConnection //Remover, já que você quer usar o @DynamicPropertySource
    public PostgreSQLContainer postgreSQLContainer() {
        return postgreSQLContainer;
    }

}