package com.example.core.player.domain;

import com.example.core.commons.exception.OperationException;
import com.example.data.model.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.core.commons.exception.messages.AccountExceptionMessage.PLAYER_UPDATE_EXCEPTION;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlayerDeleter {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Transactional
    public void deletePlayer(Long id) {
        try {
            playerRepository.deleteById(id);
        } catch (Exception e) {
            log.error(PLAYER_UPDATE_EXCEPTION.toString(), e);
            throw new OperationException(PLAYER_UPDATE_EXCEPTION, HttpStatus.BAD_REQUEST);
        }

    }
}
