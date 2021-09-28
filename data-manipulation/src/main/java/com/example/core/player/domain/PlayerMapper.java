package com.example.core.player.domain;

import com.example.core.player.api.PlayerListDto;
import com.example.core.player.api.dto.PlayerDto;
import com.example.core.team.domain.TeamMapper;
import com.example.data.model.Player;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TeamMapper.class})
public interface PlayerMapper {

    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "playerTeams", source = "teams")
    @Mapping(target = "updateDate", ignore = true)
    Player mapDtoOnCreate(PlayerDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    @Mapping(target = "playerTeams", ignore = true)
    void updatePlayerByDto(@MappingTarget Player acc, PlayerDto dto);

    PlayerListDto toPlayerListDto(Player player);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    List<PlayerListDto> toListOfPlayers(List<Player> prices);

}
