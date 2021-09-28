package com.example.core.team.domain;

import com.example.core.commons.exception.OperationException;
import com.example.core.team.api.dto.TeamDto;
import com.example.data.model.Team;
import com.example.data.model.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.core.commons.exception.messages.AccountExceptionMessage.PLAYER_CREATION_EXCEPTION;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeamCreator {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Transactional
    public void createTeam(TeamDto teamDto) {
        Team team;
        try {
            team = teamMapper.mapDtoOnCreate(teamDto);
            teamRepository.save(team);

        } catch (Exception e) {
            log.error(PLAYER_CREATION_EXCEPTION.toString(), e);
            throw new OperationException(PLAYER_CREATION_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
