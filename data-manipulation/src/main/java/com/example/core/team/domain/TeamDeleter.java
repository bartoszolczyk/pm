package com.example.core.team.domain;

import com.example.commons.exception.OperationException;
import com.example.data.model.Team;
import com.example.data.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.commons.exception.messages.SystemExceptionMessage.PLAYER_UPDATE_EXCEPTION;
import static com.example.commons.exception.messages.SystemExceptionMessage.TEAM_NOT_FOUND;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeamDeleter {

    private final TeamRepository teamRepository;

    @Transactional
    public void deleteTeam(Long id) {
        try {
            Team team = teamRepository.findById(id).orElseThrow(() -> new OperationException(TEAM_NOT_FOUND, HttpStatus.NOT_FOUND));
            team.removeAssociations();
            teamRepository.delete(team);
        } catch (Exception e) {
            log.error(PLAYER_UPDATE_EXCEPTION.toString(), e);
            throw new OperationException(PLAYER_UPDATE_EXCEPTION, HttpStatus.BAD_REQUEST, e);
        }
    }
}
