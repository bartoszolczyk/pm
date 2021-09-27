package com.example.core.player.api;

import com.example.core.player.api.dto.PlayerDto;

public interface PlayerTestUtils {

    static PlayerDto createPostPlayer() {
        return PlayerDto.builder()
            .name("Zbigniew")
            .surname("Pańczyk")
            .age(33)
            .team(null)
            .build();
    }

    static PlayerDto createUpdatedPlayer() {
        return PlayerDto.builder()
            .id(1L)
            .name("Marcin")
            .surname("Nowak")
            .age(23)
            .team(null)
            .build();
    }
}
