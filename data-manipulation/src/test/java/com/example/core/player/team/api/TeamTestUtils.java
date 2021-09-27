package com.example.core.player.team.api;

import com.example.core.player.team.api.dto.TeamDto;

import java.time.LocalDate;

public interface TeamTestUtils {

    static TeamDto createPostTeam() {
        return TeamDto.builder()
            .currency("GBP")
            .foundation(LocalDate.of(1982, 6, 3))
            .name("Liverpool")
            .build();

    }

    static TeamDto createUpdatedTeam() {
        return TeamDto.builder()
            .id(1L)
            .currency("EUR")
            .foundation(LocalDate.of(1909, 7, 24))
            .name("Borussia Dortmund")
            .build();
    }
}
