package com.example.core.player.api;

import com.example.core.player.api.dto.PlayerDto;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface PlayerTestUtils {

    static PlayerDto createPostPlayer() {
        return PlayerDto.builder()
            .name("Zbigniew")
            .surname("Adamczyk")
            .age(33)
            .teams(Stream.of(1L, 2L)
                .collect(Collectors.toList()))
            .build();
    }

    static PlayerDto createUpdatedPlayer() {
        return PlayerDto.builder()
            .id(1L)
            .name("Marcin")
            .surname("Nowak")
            .age(23)
            .teams(Stream.of(3L, 4L)
                .collect(Collectors.toList()))
            .build();
    }
}
