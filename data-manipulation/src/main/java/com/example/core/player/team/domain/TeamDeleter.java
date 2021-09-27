package com.example.core.player.team.domain;

import com.example.core.commons.exception.OperationException;
import com.example.data.model.repository.TeamRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.core.commons.exception.messages.AccountExceptionMessage.PLAYER_UPDATE_EXCEPTION;

@Slf4j
@Component
@RequiredArgsConstructor
public class TeamDeleter {

    private final TeamRepository teamRepository;

    @Transactional
    public void deleteTeam(Long id) {
        try {
            teamRepository.deleteById(id);
        } catch (Exception e) {
            log.error(PLAYER_UPDATE_EXCEPTION.toString(), e);
            throw new OperationException(PLAYER_UPDATE_EXCEPTION, HttpStatus.BAD_REQUEST, e);
        }
    }
}
