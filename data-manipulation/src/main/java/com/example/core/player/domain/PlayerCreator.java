package com.example.core.player.domain;

import com.example.core.commons.exception.OperationException;
import com.example.core.player.api.dto.PlayerDto;
import com.example.data.model.Player;
import com.example.data.model.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.core.commons.exception.messages.AccountExceptionMessage.PLAYER_CREATION_EXCEPTION;

@RequiredArgsConstructor
@Component
@Slf4j
public class PlayerCreator {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Transactional
    public void createPlayer(PlayerDto playerDto) {
        Player acc;
        try {
            acc = playerMapper.mapDtoOnCreate(playerDto);
            playerRepository.save(acc);

        } catch (Exception e) {
            log.error(PLAYER_CREATION_EXCEPTION.toString(), e);
            throw new OperationException(PLAYER_CREATION_EXCEPTION, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
