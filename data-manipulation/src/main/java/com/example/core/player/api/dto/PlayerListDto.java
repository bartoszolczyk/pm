package com.example.core.player.api.dto;

import lombok.Builder;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Value
@Builder
@Jacksonized
public class PlayerListDto {
    Long id;
    String name;
    String surname;
}
