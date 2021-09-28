package com.example.core.player.api;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
@JsonDeserialize(builder = TeamListDto.TeamListDtoBuilder.class)
public class TeamListDto {

    Long id;
    String name;
}
