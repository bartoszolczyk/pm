package com.example.core.player.domain;

import com.example.commons.exception.OperationException;
import com.example.data.model.Player;
import com.example.data.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import static com.example.commons.exception.messages.SystemExceptionMessage.EXISTING_UNRESOLVED_TRANSACTIONS_EXISTS;
import static com.example.commons.exception.messages.SystemExceptionMessage.PLAYER_NOT_FOUND;
import static com.example.commons.exception.messages.SystemExceptionMessage.PLAYER_UPDATE_EXCEPTION;

@Slf4j
@Component
@RequiredArgsConstructor
public class PlayerDeleter {

    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    @Transactional
    public void deletePlayer(Long id) {
        try {
            Player player = playerRepository.findById(id).orElseThrow(() -> new OperationException(PLAYER_NOT_FOUND, HttpStatus.NOT_FOUND));
            player.removeAssociations();
            if (!player.getTransferTransactions().isEmpty()) {
                throw new OperationException(EXISTING_UNRESOLVED_TRANSACTIONS_EXISTS, HttpStatus.METHOD_NOT_ALLOWED);
            }
            playerRepository.delete(player);
        } catch (OperationException e) {
            throw e;
        } catch (Exception e) {
            log.error(PLAYER_UPDATE_EXCEPTION.toString(), e);
            throw new OperationException(PLAYER_UPDATE_EXCEPTION, HttpStatus.BAD_REQUEST);
        }

    }
}
