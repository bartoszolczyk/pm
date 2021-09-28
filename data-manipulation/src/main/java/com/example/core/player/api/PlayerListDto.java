package com.example.core.player.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = PlayerListDto.PlayerListDtoBuilder.class)
public class PlayerListDto {
    Long id;
    String name;
    String surname;
}
