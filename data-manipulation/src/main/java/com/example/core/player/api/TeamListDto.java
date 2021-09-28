package com.example.core.player.api;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TeamListDto {

    Long id;
    String name;
}
