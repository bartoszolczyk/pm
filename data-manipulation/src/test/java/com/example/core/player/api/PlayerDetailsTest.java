package com.example.core.player.api;

import com.example.core.IntegrationTestConfig;
import com.example.core.player.domain.PlayerMapper;
import com.example.data.model.repository.PlayerRepository;
import com.example.data.model.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class PlayerDetailsTest extends IntegrationTestConfig implements PlayerTestUtils {

    private final static String URI = "/player/v1";

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    PlayerMapper playerMapper;

    @BeforeEach
    void init() {
        ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
        databasePopulator.setSqlScriptEncoding("UTF-8");
        databasePopulator.addScript(new ClassPathResource("/sql/initPlayersAndTeams.sql"));
        databasePopulator.execute(dataSource);
    }

    @Test
    void getAllPlayers() throws Exception {

        ResultActions out = mockMvc.perform(MockMvcRequestBuilders.get(URI + "/all")
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding(StandardCharsets.UTF_8));

        MockHttpServletResponse response = out.andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        List<PlayerListDto> listDtos = Arrays.asList(ObjectMapperHolder.instance.readValue(response.getContentAsString(), PlayerListDto[].class));
        assertEquals(4, listDtos.size());
        Optional<PlayerListDto> elem = listDtos.stream()
            .filter(playerListDto -> playerListDto.getId().equals(1L))
            .findFirst();
        assertTrue(elem.isPresent());
        assertEquals("Adam", elem.get().getName());
        assertEquals("Adamczyk", elem.get().getSurname());

    }

    @Test
    void getPlayerTeams() throws Exception {
        ResultActions out = mockMvc.perform(MockMvcRequestBuilders.get(URI +"/{id}/team-details",1L)
            .accept(MediaType.APPLICATION_JSON)
            .characterEncoding(StandardCharsets.UTF_8));

        MockHttpServletResponse response = out.andReturn().getResponse();
        assertEquals(HttpStatus.OK.value(), response.getStatus());

        List<TeamListDto> listDtos = Arrays.asList(ObjectMapperHolder.instance.readValue(response.getContentAsString(), TeamListDto[].class));
        assertEquals(2, listDtos.size());
        Optional<TeamListDto> elem = listDtos.stream()
            .filter(playerListDto -> playerListDto.getId().equals(2L))
            .findFirst();
        assertTrue(elem.isPresent());
        assertEquals("Borrusia Dortmund", elem.get().getName());
    }

}