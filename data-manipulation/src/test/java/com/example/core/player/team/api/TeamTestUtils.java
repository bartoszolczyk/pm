package com.example.core.player.team.api;

import com.example.core.team.api.dto.TeamDto;

import java.math.BigDecimal;
import java.time.LocalDate;

public interface TeamTestUtils {

    static TeamDto createPostTeam() {
        return TeamDto.builder()
            .currency("GBP")
            .foundation(LocalDate.of(1982, 6, 3))
            .name("Liverpool")
            .provision(BigDecimal.valueOf(0.09))
            .balance(BigDecimal.valueOf(100000))
            .build();

    }

    static TeamDto createUpdatedTeam() {
        return TeamDto.builder()
            .id(1L)
            .currency("EUR")
            .foundation(LocalDate.of(1909, 7, 24))
            .name("Borussia Dortmund")
            .provision(BigDecimal.valueOf(0.03))
            .balance(BigDecimal.valueOf(500000))
            //TODO: think if balance should be changed
            .build();
    }
}
