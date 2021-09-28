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

import static com.example.core.commons.exception.messages.AccountExceptionMessage.PLAYER_NOT_FOUND;
import static com.example.core.commons.exception.messages.AccountExceptionMessage.PLAYER_UPDATE_EXCEPTION;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeamUpdater {

    private final TeamRepository teamRepository;
    private final TeamMapper teamMapper;

    @Transactional
    public void updateTeam(TeamDto dto) {
        Team team = teamRepository.findById(dto.getId()).orElseThrow(() -> new OperationException(PLAYER_NOT_FOUND, HttpStatus.NOT_FOUND));
        try {
            teamMapper.updateTeamByDto(team, dto);
            teamRepository.save(team);
        } catch (Exception e) {
            log.error(PLAYER_UPDATE_EXCEPTION.toString(), e);
            throw new OperationException(PLAYER_UPDATE_EXCEPTION, HttpStatus.BAD_REQUEST);
        }

    }
}
