package com.example.core.player.api;

import com.example.core.IntegrationTestConfig;
import com.example.core.player.domain.PlayerMapper;
import com.example.data.model.Player;
import com.example.data.model.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class PlayerControllerTest extends IntegrationTestConfig implements PlayerTestUtils {

    private final static String URI = "/player/v1";

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerMapper playerMapper;

    @Test
    void createPlayer() throws Exception {

        ResultActions out = mockMvc.perform(
            MockMvcRequestBuilders.post(URI).content(asJsonString(PlayerTestUtils.createPostPlayer())).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        Player player = playerRepository.getById(1L);

        Assertions.assertEquals(HttpStatus.CREATED.value(), out.andReturn().getResponse().getStatus());
        Assertions.assertEquals("Zbigniew", player.getName());
        Assertions.assertEquals("Pańczyk", player.getSurname());
        Assertions.assertEquals(33, player.getAge());
    }

    @Test
    void updatePlayer() throws Exception {

        playerRepository.save(playerMapper.mapDtoOnCreate(PlayerTestUtils.createPostPlayer()));

        ResultActions out = mockMvc.perform(
            MockMvcRequestBuilders.put(URI).content(asJsonString(PlayerTestUtils.createUpdatedPlayer())).contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON));

        Player player = playerRepository.getById(1L);

        Assertions.assertEquals(HttpStatus.OK.value(), out.andReturn().getResponse().getStatus());
        Assertions.assertEquals("Marcin", player.getName());
        Assertions.assertEquals("Nowak", player.getSurname());
        Assertions.assertEquals(23, player.getAge());
    }

    @Test
    void deletePlayer() throws Exception {
        playerRepository.save(playerMapper.mapDtoOnCreate(PlayerTestUtils.createPostPlayer()));
        ResultActions out = mockMvc.perform(MockMvcRequestBuilders.delete(URI+"/{id}",1 ).accept(MediaType.APPLICATION_JSON));

        Assertions.assertEquals(HttpStatus.OK.value(), out.andReturn().getResponse().getStatus());
        Assertions.assertFalse(playerRepository.existsById(1L));

    }
}