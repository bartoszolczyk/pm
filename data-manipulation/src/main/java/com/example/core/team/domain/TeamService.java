package com.example.core.team.domain;

import com.example.core.player.api.dto.TeamListDto;
import com.example.core.team.api.dto.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamService {

    private final TeamCreator teamCreator;
    private final TeamUpdater teamUpdater;
    private final TeamDeleter teamDeleter;

    public void addNewTeam(TeamDto teamDto) {
        teamCreator.createTeam(teamDto);
    }

    @Transactional
    public void updateTeam(TeamDto teamDto) {

        teamUpdater.updateTeam(teamDto);
    }

    @Transactional
    public void deleteTeam(Long teamId) {
        teamDeleter.deleteTeam(teamId);
    }

    public List<TeamListDto> getPlayerTeams(Long playerId) {
        return null;
    }
}

    


