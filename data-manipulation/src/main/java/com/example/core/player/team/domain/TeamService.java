package com.example.core.player.team.domain;

import com.example.core.player.team.api.dto.TeamDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class TeamService {

    @Transactional
    public void addNewTeam(TeamDto accountDto) {
        // TODO document why this method is empty
    }

    @Transactional
    public void updateTeam(TeamDto playerDto) {
    }

    @Transactional
    public void deleteTeam(Long teamId) {
    }


}
