package com.example.core.player.domain;

import com.example.core.player.api.dto.PlayerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerCreator playerCreator;

    public void addNewPlayer(PlayerDto accountDto) {
        playerCreator.createPlayer(accountDto);
    }

    @Transactional
    public void updatePlayer(PlayerDto playerDto) {
    }

    @Transactional
    public void deletePlayer(Long playerId) {
    }
}
