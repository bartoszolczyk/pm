package com.example.core.player.domain;

import com.example.core.player.api.dto.PlayerDto;
import com.example.data.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", ignore = true)
    Player mapDtoOnCreate(PlayerDto dto);
}
