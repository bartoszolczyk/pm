package com.example.core.player.domain;

import com.example.commons.exception.OperationException;
import com.example.core.player.api.dto.PlayerDto;
import com.example.data.model.Player;
import com.example.data.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.commons.exception.messages.SystemExceptionMessage.PLAYER_NOT_FOUND;
import static com.example.commons.exception.messages.SystemExceptionMessage.PLAYER_UPDATE_EXCEPTION;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlayerUpdater {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Transactional
    public void updatePlayer(PlayerDto dto) {
        Player player = playerRepository.findById(dto.getId()).orElseThrow(() -> new OperationException(PLAYER_NOT_FOUND, HttpStatus.NOT_FOUND));
        try {
            playerMapper.updatePlayerByDto(player, dto);
            playerRepository.save(player);
        } catch (Exception e) {
            log.error(PLAYER_UPDATE_EXCEPTION.toString(), e);
            throw new OperationException(PLAYER_UPDATE_EXCEPTION,HttpStatus.BAD_REQUEST);
        }

    }
}
