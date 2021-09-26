package com.example.core.player.api;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.utility.DockerImageName;

@Testcontainers
@SpringBootTest
class PlayerControllerTest {

    @Container
    public static PostgreSQLContainer container = new PostgreSQLContainer(DockerImageName.parse("postgres"))
        .withDatabaseName("test")
        .withPassword("postgres")
        .withUsername("postgres");

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", container::getJdbcUrl);
        registry.add("spring.datasource.password", container::getPassword);
        registry.add("spring.datasource.username", container::getUsername);
    }

    @BeforeEach
    void setUp() {

    }

    @Test
    void createPlayer() {
//        Client client = ClientBuilder.newBuilder().build();
//        Response returnState = client.target("http://localhost:8080/restex/123123123")
//            .request(MediaType.APPLICATION_JSON).get(Response.class);
//        assertEquals(404, returnState.getStatus());
    }

    @Test
    void updatePlayer() {
    }

    @Test
    void deletePlayer() {
    }
}