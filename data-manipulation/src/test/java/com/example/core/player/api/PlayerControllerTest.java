package com.example.core.player.api;

import com.example.core.SpringGeneralTests;
import com.example.core.player.api.dto.PlayerDto;
import com.example.data.model.Player;
import com.example.data.model.repository.PlayerRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

class PlayerControllerTest extends SpringGeneralTests {

    private final static String URI = "/player/v1";

    @Autowired
    PlayerRepository playerRepository;

    @BeforeEach
    void setUp() {

    }

    @Test
    void createPlayer() throws Exception {

        ResultActions out = mockMvc.perform(MockMvcRequestBuilders
            .post(URI)
            .content(asJsonString(PlayerDto.builder()
                .id(-1L)
                .name("Zbigniew")
                .surname("Pańczyk")
                .age(33)
                .team(null)
                .build()
            ))
            .contentType(MediaType.APPLICATION_JSON)
            .accept(MediaType.APPLICATION_JSON));

        Player player = playerRepository.getById(1L);

        Assertions.assertEquals(out.andReturn().getResponse().getStatus(), HttpStatus.CREATED.value());
        Assertions.assertEquals("Zbigniew",player.getName() );
        Assertions.assertEquals("Pańczyk",player.getSurname() );
        Assertions.assertEquals(33,player.getAge() );
    }

    @Test
    void updatePlayer() {
    }

    @Test
    void deletePlayer() {
    }
}