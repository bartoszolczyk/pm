package com.example.core.player.domain;

import com.example.core.player.api.dto.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerCreator playerCreator;
    private final PlayerUpdater playerUpdater;
    private final PlayerDeleter playerDeleter;


    public void addNewPlayer(PlayerDto playerDto) {
        playerCreator.createPlayer(playerDto);
    }

    @Transactional
    public void updatePlayer(PlayerDto playerDto) {

        playerUpdater.updatePlayer(playerDto) ;
    }

    @Transactional
    public void deletePlayer(Long playerId) {
        playerDeleter.deletePlayer(playerId);
    }
}
