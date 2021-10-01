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

    private final static String PLAYER_URI = "/player/v1";
    private final static String TEAM_URI = "/team/v1";

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
    void deletePlayerWithTeam() throws Exception {

        Assertions.assertNotNull(playerRepository.getById(4L));
        ResultActions out = mockMvc.perform(MockMvcRequestBuilders.delete(PLAYER_URI + "/{id}", 4).accept(MediaType.APPLICATION_JSON));

        Assertions.assertEquals(HttpStatus.OK.value(), out.andReturn().getResponse().getStatus());
        Assertions.assertTrue(teamRepository.existsById(4L));
        Assertions.assertFalse(playerRepository.existsById(4L));

    }

    @Test
    void deleteTeamWithPlayer() throws Exception {
        Assertions.assertNotNull(teamRepository.getById(4L));
        ResultActions out = mockMvc.perform(MockMvcRequestBuilders.delete(TEAM_URI + "/{id}", 4).accept(MediaType.APPLICATION_JSON));

        Assertions.assertEquals(HttpStatus.OK.value(), out.andReturn().getResponse().getStatus());
        Assertions.assertFalse(teamRepository.existsById(4L));
        Assertions.assertTrue(playerRepository.existsById(4L));

    }

    @Test
    void deletePlayerWithTeamAndTransaction() throws Exception {

        Assertions.assertNotNull(playerRepository.getById(2L));
        ResultActions out = mockMvc.perform(MockMvcRequestBuilders.delete(PLAYER_URI + "/{id}", 2).accept(MediaType.APPLICATION_JSON));

        Assertions.assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), out.andReturn().getResponse().getStatus());
        Assertions.assertTrue( out.andReturn().getResponse().getContentAsString().contains("METHOD_NOT_ALLOWED reason: EXISTING_UNRESOLVED_TRANSACTIONS_EXISTS"));

    }

    @Test
    void deleteTeamWithPlayerAndTransaction() throws Exception {
        Assertions.assertNotNull(teamRepository.getById(2L));
        ResultActions out = mockMvc.perform(MockMvcRequestBuilders.delete(TEAM_URI + "/{id}", 1).accept(MediaType.APPLICATION_JSON));

        Assertions.assertEquals(HttpStatus.METHOD_NOT_ALLOWED.value(), out.andReturn().getResponse().getStatus());
        Assertions.assertTrue( out.andReturn().getResponse().getContentAsString().contains("METHOD_NOT_ALLOWED reason: EXISTING_UNRESOLVED_TRANSACTIONS_EXISTS"));
    }

}
