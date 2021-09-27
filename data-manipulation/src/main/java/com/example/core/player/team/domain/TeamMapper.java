package com.example.core.player.team.domain;

import com.example.core.player.api.dto.PlayerDto;
import com.example.core.player.team.api.dto.TeamDto;
import com.example.data.model.Player;
import com.example.data.model.Team;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface TeamMapper {


    @Mapping(target = "players", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Team mapDtoOnCreate(TeamDto dto);

    @Mapping(target = "players", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    void updateTeamByDto(@MappingTarget Team team, TeamDto dto);

}
