package com.example.core.transfer.api;

import com.example.core.IntegrationTestConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TransactionControllerTest extends IntegrationTestConfig implements TransactionUtils {

    private final static String URI = "/transaction/v1";

    @BeforeEach
    void setUp() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setSqlScriptEncoding("UTF-8");
        databasePopulator.addScript(new ClassPathResource("/sql/initPlayersAndTeams.sql"));
        databasePopulator.execute(dataSource);
    }

    @Test
    void crateGoodTransaction() throws Exception {

        ResultActions out = mockMvc.perform(
            MockMvcRequestBuilders.post(URI).content(asJsonString(TransactionUtils.createPostTransaction())).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        assertEquals(HttpStatus.OK.value(), out.andReturn().getResponse().getStatus());

    }
}