package com.example.core.player.domain;

import com.example.core.player.api.dto.PlayerDto;
import com.example.data.model.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "team", ignore = true)
    @Mapping(target = "updateDate", ignore = true)
    Player mapDtoOnCreate(PlayerDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "team", ignore = true)
    void updatePlayerByDto(@MappingTarget Player acc, PlayerDto dto);
}
