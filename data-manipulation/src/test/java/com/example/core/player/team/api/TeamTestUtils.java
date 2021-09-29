package com.example.core.player.team.api;

import com.example.core.team.api.dto.TeamDto;

import java.math.BigDecimal;

public interface TeamTestUtils {

    static TeamDto createPostTeam() {
        return TeamDto.builder()
            .currency("GBP")
            .name("Liverpool")
            .provision(BigDecimal.valueOf(0.09))
            .balance(BigDecimal.valueOf(100000))
            .build();

    }

    static TeamDto createUpdatedTeam() {
        return TeamDto.builder()
            .id(1L)
            .currency("EUR")
            .name("Borussia Dortmund")
            .provision(BigDecimal.valueOf(0.03))
            .balance(BigDecimal.valueOf(500000))
            .build();
    }
}
