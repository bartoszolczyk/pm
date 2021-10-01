package com.example.core.player.api;

import com.example.core.IntegrationTestConfig;
import com.example.core.player.domain.PlayerMapper;
import com.example.data.repository.PlayerRepository;
import com.example.data.repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class PlayerTeamDeleteTest extends IntegrationTestConfig {

    private final static String URI = "/player/v1";

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerMapper playerMapper;

    @BeforeEach
    private void prepareDeleteTeamData() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setSqlScriptEncoding("UTF-8");
        databasePopulator.addScript(new ClassPathResource("/sql/deleteTeam.sql"));
        databasePopulator.execute(dataSource);
    }

    @Test
    void deleteTeamWithPlayer() throws Exception {

        Assertions.assertNotNull(playerRepository.getById(1L));
        ResultActions out = mockMvc.perform(MockMvcRequestBuilders.delete(URI + "/{id}", 1).accept(MediaType.APPLICATION_JSON));

        Assertions.assertEquals(HttpStatus.OK.value(), out.andReturn().getResponse().getStatus());
        Assertions.assertTrue(teamRepository.existsById(1L));
        Assertions.assertFalse(playerRepository.existsById(1L));

    }
}
