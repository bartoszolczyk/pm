package com.example.core.player.team.api;

import com.example.core.IntegrationTestConfig;
import com.example.core.team.domain.TeamMapper;
import com.example.data.model.Team;
import com.example.data.repository.TeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

class TeamControllerTest extends IntegrationTestConfig implements TeamTestUtils {

    private final static String URI = "/team/v1";

    @Autowired
    TeamRepository teamRepository;

    @Autowired
    TeamMapper teamMapper;

    @Test
    void createTeam() throws Exception {

        ResultActions out = mockMvc.perform(
            MockMvcRequestBuilders.post(URI).content(asJsonString(TeamTestUtils.createPostTeam())).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        Team team = teamRepository.getById(1L);

        Assertions.assertEquals(HttpStatus.CREATED.value(), out.andReturn().getResponse().getStatus());
        Assertions.assertEquals("Liverpool", team.getName());
        Assertions.assertEquals("GBP", team.getCurrency().getCurrencyCode());
        Assertions.assertEquals(BigDecimal.valueOf(100000.00).setScale(2, RoundingMode.HALF_EVEN), team.getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(0.09).setScale(4, RoundingMode.HALF_EVEN), team.getProvision());
        Assertions.assertEquals(LocalDate.of(2021,9,30), team.getCreationDate());

    }

    @Test
    void updateTeam() throws Exception {

        teamRepository.save(teamMapper.mapDtoOnCreate(TeamTestUtils.createPostTeam()));

        ResultActions out = mockMvc.perform(
            MockMvcRequestBuilders.put(URI).content(asJsonString(TeamTestUtils.createUpdatedTeam())).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        Team team = teamRepository.getById(1L);

        Assertions.assertEquals(HttpStatus.OK.value(), out.andReturn().getResponse().getStatus());
        Assertions.assertEquals("Borussia Dortmund", team.getName());
        Assertions.assertEquals("EUR", team.getCurrency().getCurrencyCode());
        Assertions.assertEquals(BigDecimal.valueOf(500000.00).setScale(2, RoundingMode.HALF_EVEN), team.getBalance());
        Assertions.assertEquals(BigDecimal.valueOf(0.03).setScale(4, RoundingMode.HALF_EVEN), team.getProvision());
        Assertions.assertEquals(LocalDate.of(2021,9,30), team.getCreationDate());
    }

    @Test
    void deleteTeam() throws Exception {
        teamRepository.save(teamMapper.mapDtoOnCreate(TeamTestUtils.createPostTeam()));
        Assertions.assertNotNull(teamRepository.getById(1L));
        ResultActions out = mockMvc.perform(MockMvcRequestBuilders.delete(URI+"/{id}",1 ).accept(MediaType.APPLICATION_JSON));

        Assertions.assertNotNull(teamRepository.getById(1L));
        Assertions.assertEquals(HttpStatus.OK.value(), out.andReturn().getResponse().getStatus());
        Assertions.assertFalse(teamRepository.existsById(1L));

    }

}