package com.example.core.team.domain;

import com.example.core.player.api.dto.TeamListDto;
import com.example.core.team.api.dto.TeamDto;
import com.example.data.model.Team;
import com.example.data.model.repository.TeamRepository;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValueMappingStrategy;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public abstract class TeamMapper {

    @Autowired
    TeamRepository teamRepository;

    @Mapping(target = "players", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    public abstract Team mapDtoOnCreate(TeamDto dto);

    @Mapping(target = "players", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creationDate", ignore = true)
    public abstract void updateTeamByDto(@MappingTarget Team team, TeamDto dto);

    public abstract TeamListDto toTeamListDto(Team team);

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    public abstract List<TeamListDto> mapPlayerTeamsToDto(Set<Team> playerTeams);

    public Team teamById(Long id) {
        return teamRepository.getById(id);
    }

    @IterableMapping(nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT)
    public abstract Set<Team> productTypeSetById(Set<Long> longSet);

}
