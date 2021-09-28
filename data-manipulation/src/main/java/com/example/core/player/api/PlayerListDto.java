package com.example.core.player.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class PlayerListDto {
    Long id;
    String name;
    String surname;
}
