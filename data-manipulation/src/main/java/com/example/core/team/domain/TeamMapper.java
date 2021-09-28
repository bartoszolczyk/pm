package com.example.core.team.domain;

import com.example.core.player.api.TeamListDto;
import com.example.core.team.api.dto.TeamDto;
import com.example.data.model.Team;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface TeamMapper {

    @Mapping(target = "players", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    Team mapDtoOnCreate(TeamDto dto);

    @Mapping(target = "players", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    void updateTeamByDto(@MappingTarget Team team, TeamDto dto);

    TeamListDto toTeamListDto(Team team);
    
    @IterableMapping(nullValueMappingStrategy =  NullValueMappingStrategy.RETURN_DEFAULT)
    List<TeamListDto> mapPlayerTeamsToDto(Set<Team> playerTeams);
}
