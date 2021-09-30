package com.example.core.player.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class TeamListDto {

    Long id;
    String name;
}
